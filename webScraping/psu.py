import MySQLdb
import requests
from selenium import webdriver
from bs4 import BeautifulSoup
import time
import re

HOST = "localhost"
USERNAME = "root"
PASSWORD = ""
DATABASE = "pcworld"

# Open database dbection
db = MySQLdb.connect(HOST, USERNAME, PASSWORD, DATABASE)

driver = webdriver.Chrome('./venv/Lib/site-packages/chromedriver.exe')
driver.get('https://pcpartpicker.com/products/power-supply/')
time.sleep(1)
print('start')
page_source = driver.page_source

soup = BeautifulSoup(page_source, "html.parser")

items = soup.find_all(class_="tr__product")
for item in items:
    img = item.find(class_='td__image')
    img1 = img.img['src']
    print(img1)
    
    name = item.find(class_='td__nameWrapper')
    name1 = name.p.text
    brand, *name2 = name1.split(" ",1)
    model = " ".join(name2)
    if re.search(brand, 'PC', re.IGNORECASE):
        continue
    if re.search(brand, 'Athena', re.IGNORECASE):
        continue
    if re.search(brand, 'be', re.IGNORECASE):
        continue
    if re.search(brand, 'Cooler', re.IGNORECASE):
        brand = "Cooler Master"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        modelZ = " ".join(modelY)
        model = modelZ.replace("Master", " ")
    # if re.search(brand, 'be', re.IGNORECASE):
    #     brand = "be quiet!"
    #     modelX = " ".join(name2)
    #     modeltemp, *modelY = modelX.split(" ", 1)
    #     modelZ = " ".join(modelY)
    #     model = modelZ.replace("quiet!", "WTFFFFFF")
    print("Brand : " + brand)
    print("Model : " + model)

    form = item.find(class_='td__spec--1')
    form1 = form.contents[1]
    print(form1)

    try:
        rating = item.find(class_='td__spec--2')
        rating1 = rating.contents[1]
        print(rating1)
    except:
        continue
    
    watt = item.find(class_='td__spec--3')
    wattX = watt.contents[1].split()
    watt1 = int(wattX[0])
    print(watt1)
    
    modular = item.find(class_='td__spec--4')
    modular1 = modular.contents[1]
    print(modular1)

    try:
        color = item.find(class_='td__spec--5')
        color1 = color.contents[1]
        print(color1)
    except:
        continue

    price = item.find(class_='td__price')
    pricetemp = price.find(text=re.compile("^\$"))
    if pricetemp==None:
        continue
    price1 = float(pricetemp.strip('$'))*4
    print(price1)

    cursor = db.cursor()
    sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    param1 = (brand, model, price1, img1, "PSU")
    sql2 = "insert into psu (id, wattage, efficiency, modularity, color) VALUES(%s,%s,%s,%s,%s)"

    try:
        cursor.execute(sql1, param1)
        last_id = cursor.lastrowid
        param2 = (last_id, watt1, rating1, modular1, color1)
        cursor.execute(sql2, param2)
        db.commit()
    except MySQLdb.Error as err:
        print(err)