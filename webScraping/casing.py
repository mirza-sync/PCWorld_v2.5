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
driver.get('https://pcpartpicker.com/products/case/')
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
    if re.search(brand, 'Empire', re.IGNORECASE):
        continue
    if re.search(brand, 'FSP', re.IGNORECASE):
        continue
    if re.search(brand, 'Metallic', re.IGNORECASE):
        continue
    if re.search(brand, 'Lian', re.IGNORECASE):
        brand = "Lian Li"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        model = " ".join(modelY)
    if re.search(brand, 'In Win', re.IGNORECASE):
        brand = "In Win"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        model = " ".join(modelY)
    if re.search(brand, 'Fractal', re.IGNORECASE):
        brand = "Fractal Design"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        model = " ".join(modelY)
    if re.search(brand, 'Cooler', re.IGNORECASE):
        brand = "Cooler Master"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        model = " ".join(modelY)
    if re.search(brand, 'be', re.IGNORECASE):
        brand = "be quiet!"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        model = " ".join(modelY)
    print("Brand : " + brand)
    print("Model : " + model)

    form = item.find(class_='td__spec--1')
    formX = form.contents[1]
    # IGNORING MINI!!!
    # if "mini" in formX.lower():
    #     formY = formX.split()[:2]
    #     form1 = " ".join(formY)
    # else:
    #     formZ = formX.split()
    #     form1 = formZ[0]
    if "mini" in formX.lower():
        continue
    else:
        formZ = formX.split()
        form1 = formZ[0]
    print(form1)
    quit()
    
    try:
        color = item.find(class_='td__spec--2')
        color1 = color.contents[1]
        print(color1)
    except:
        continue

    price = item.find(class_='td__price')
    pricetemp = price.find(text=re.compile("^\$"))
    if pricetemp==None:
        continue
    price1 = float(pricetemp.strip('$'))*4
    print(999999 + price1)

    cursor = db.cursor()
    sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    param1 = (brand, model, price1, img1, "Casing")
    sql2 = "insert into casing (id, formfactor, color) VALUES(%s,%s,%s)"

    try:
        cursor.execute(sql1, param1)
        last_id = cursor.lastrowid
        param2 = (last_id, form1, color1)
        cursor.execute(sql2, param2)
    except MySQLdb.Error as err:
        print(err)