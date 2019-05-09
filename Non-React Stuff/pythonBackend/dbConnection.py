import pymysql.cursors

def getConnection():

    conn = pymysql.connect(host = 'mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com',
                           user = 'cs48_ajara',
                           password = 'ajara2019',
                           db = 'recipull_rds_db',
                           charset = 'utf8mb4',
                           cursorclass = pymysql.cursors.DictCursor)

    return conn

conn = getConnection()

print("connect successful!")



def main():
    try:
        with conn.cursor() as cursor:
            sql = "SELECT * FROM recipes"
            cursor.execute(sql)
            print("cursor.description: ", cursor.description)
            print()
            for row in cursor:
                print(row)

    finally:
        conn.close()

if __name__ == "__main__":
    main()