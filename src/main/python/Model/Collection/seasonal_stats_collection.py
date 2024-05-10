import datetime
import json
import pandas as pd
from url_lists import short_hand as ab, defense_list


def build_csv(csv_type: str, url_list: list, team_url: str) -> None:
    year = datetime.date.today().year
    id_list = [year - 2003 - x for x in range(3)]
    df_list = list()

    with open(team_url, 'r') as file:
        teams = json.load(file)

    for i in range(len(url_list)):
        for year_id in id_list:
            url = url_list[i].replace('!', str(year_id))

            # Get the column name
            col_name = ab[url.split('/')[-1].split('?')[0]]
            year = int(url.split('/')[-1].split('?')[1].split('=')[1]) + 2001

            # Modifying the dataframe
            df = pd.read_html(url)[0]
            df['Season'] = year
            df.drop(columns=['Rank'], inplace=True)
            df = df[['Player', 'Season', 'Team', 'Pos', 'Value']]
            df.rename(columns={'Value': col_name}, inplace=True)
            df['Team'] = df['Team'].map(teams['team_to_abbreviation'])
            df_list.append(df)

    df = df_list[0]

    for i in range(1, len(df_list)):
        on = {'Player', 'Team', 'Season', 'Pos', df.columns[-1]}

        new_df = df_list[i]
        if new_df.columns[-1] != df.columns[-1]:
            on.remove(df.columns[-1])

        df = pd.merge(df, new_df, on=list(on), how='outer')

    df.to_csv(f'../../CSVs/Seasonally/{csv_type}.csv')


build_csv('defense', defense_list, '../../../Utils/TeamConversions.json')


