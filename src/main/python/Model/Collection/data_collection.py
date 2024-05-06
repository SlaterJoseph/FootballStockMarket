import requests
import csv
import json


def create_player_csv():
    """
    A function which calls the RapidAPI which generates player data
    CSV Format [longName, playerID, exp, pos, age, team, teamID, espnHeadshot, isFreeAgent]
    :return: None
    """
    # Writes the initial header line
    headers = ['Name', 'PlayerID', 'Experience', 'Height', 'Weight', 'Position', 'Age', 'Team', 'TeamID', 'isFreeAgent',
               'Headshot', 'espnIdFull']
    writer = csv.writer(open('../../CSVs/players.csv', 'w', newline=''))
    writer.writerow(headers)
    i = 0

    writer = csv.writer(open('../../CSVs/players.csv', 'a', newline=''))

    url = "https://tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com/getNFLPlayerList"
    headers = {
        "X-RapidAPI-Key": "236df6219amshc8fa87454bedfc1p11c1f6jsnde793b62943f",
        "X-RapidAPI-Host": "tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com"
    }

    fieldNames = ['longName', 'playerID', 'exp', 'height', 'weight', 'pos', 'age', 'team', 'teamID', 'isFreeAgent',
                  'espnHeadshot', 'espnIDFull']
    response = json.loads(requests.get(url, headers=headers).text)

    for item in response['body']:
        player_info = list()

        for field in fieldNames:
            if field not in item.keys():
                player_info.append('N/A')
            else:
                player_info.append(item[field])

        print(item['longName'])
        writer.writerow(player_info)


create_player_csv()
