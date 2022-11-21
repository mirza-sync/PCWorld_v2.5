# from pcpartpicker import API

# api = API()
# cpu_data = api.retrieve("video-card")

# print(cpu_data->brand)

import MySQLdb

HOST = "localhost"
USERNAME = "root"
PASSWORD = ""
DATABASE = "pcworld"

# Open database connection
db = MySQLdb.connect(HOST, USERNAME, PASSWORD, DATABASE)
# cursor = db.cursor()
# cursor.execute("select database();")
# row = cursor.fetchone()
# numrows = cursor.rowcount
# if numrows > 0:
#     print(row[0])
#     print("Success")
# else:
#     print("Failed")
# db.close()