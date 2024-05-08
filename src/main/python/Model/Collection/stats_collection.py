import csv
import requests
import json
import datetime

year = datetime.date.today().year


def get_player_stats() -> None:
    """
    A function which webs scrapes ESPN to compile a list of the last 3 seasons stats for every
    wanted NFL player
    :return: None
    """
    csvs = initiate_csvs()

    # Reading in the data from our player CSV
    players = csv.reader(open('../../CSVs/players.csv'))
    player_headers = next(players)
    player_list = list()

    for player in players:
        player_list.append(player)

    seasons = [str(year - 1), str(year - 2), str(year - 3)]

    # Iterating over players grabbing their stats over the 3 previous seasons
    for player in player_list:
        print(player[0])

        espn_id = player[11]
        for season in seasons:
            url = f'https://sports.core.api.espn.com/v2/sports/football/leagues/nfl/' \
                  f'seasons/{season}/athletes/{espn_id}/eventlog'
            response = json.loads(requests.get(url).text)

            if len(response) > 1:
                for item in response['events']['items']:
                    if 'statistics' in item:
                        stats_url = item['statistics']['$ref']
                        stats = json.loads(requests.get(stats_url).text)

                        for entry in stats['splits']['categories']:
                            cat = entry['name']
                            performance = list()

                            for stat in entry['stats']:
                                performance.append(stat['value'])

                            csvs[cat].writerow(performance)
                    # Did not play a game that season
                    else:
                        continue


def build_csv_headers() -> {str: list}:
    """
    Builds the headers for the different csvs
    :return: A dictionary of str : list pairs, where the key is the csv name and the list is the header
    """
    # Just a single player url for reference
    url = 'http://sports.core.api.espn.com/v2/sports/football/leagues/nfl/events/401547408/competitions/401547408/competitors/26/roster/4567048/statistics/0?lang=en&region=us'

    response = json.loads(requests.get(url).text)
    csv_headers = dict()

    for entry in response['splits']['categories']:
        title = entry['name']
        stats = [chunk['abbreviation'] for chunk in entry['stats']]

        csv_headers[title] = stats

    return csv_headers


def initiate_csvs() -> {str: csv.writer}:
    """
    Initializes the csv writers for the different csvs
    :return: A dictionary of str : writers, where the key is the name and the value is the csv writer
    """
    csvs_titles = ['general', 'passing', 'rushing', 'receiving', 'defensive', 'defensiveInterceptions', 'kicking',
                   'returning', 'punting', 'scoring']

    csv_headers = build_csv_headers()
    csvs = dict()

    for title in csvs_titles:
        writer = csv.writer(open(f'../../CSVs/{title}.csv', 'a', newline=''))
        writer.writerow(csv_headers[title])
        csvs[title] = writer

    return csvs


get_player_stats()

"""
Different CSVs
General
Passing
Rushing
Receiving
Defensive
Defensive Interceptions
Kicking
Returning
Punting
Scoring
"""
