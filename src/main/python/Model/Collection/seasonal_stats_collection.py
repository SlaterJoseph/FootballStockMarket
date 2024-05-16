import datetime
import glob
import os
import pandas as pd
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from url_lists import season_stat_urls, url_pairings
from io import StringIO

years = datetime.date.today().year
years = [years - x - 1 for x in range(3)]


def build_csv_seasonal():
    try:
        files = glob.glob(os.path.join('../../CSVs/Seasonally', '*'))
        for file in files:
            if os.path.isfile(file):
                os.remove(file)
    except OSError:
        print("Error occurred while deleting files.")

    service = Service()
    options = webdriver.ChromeOptions()
    driver = webdriver.Chrome(service=service, options=options)

    for url, name in zip(season_stat_urls, url_pairings):
        df = None

        for year in years:
            used_url = url.replace('!', str(year))
            print(used_url)
            driver.get(used_url)

            show_more = driver.find_element(By.LINK_TEXT, 'Show More')
            while show_more:
                show_more.click()
                driver.implicitly_wait(0.3)

                try:
                    show_more = driver.find_element(By.LINK_TEXT, 'Show More')

                # To stop the rest of the Show Mores
                except Exception:
                    print('No more Show Mores')
                    show_more = False
                    continue

            dfs = pd.read_html(StringIO(driver.page_source))


            names = dfs[0]
            stats = dfs[1]

            # Cleaning up and merging the dataframes
            names.drop('RK', axis=1, inplace=True)
            names[['Name', 'Team']] = names['Name'].str.extract(r'^(.*?)([A-Z]{2,3})$')
            names['Season'] = year
            names = names.merge(stats, left_index=True, right_index=True)

            if year != years[0]:
                df = pd.concat([df, names], axis=0)
            else:
                df = names

        df.to_csv(f'../../CSVs/Seasonally/{name}.csv')
    driver.quit()


build_csv_seasonal()
