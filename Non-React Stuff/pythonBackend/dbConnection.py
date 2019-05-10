import pymysql.cursors

class dbConnection:
    def __init__(self):
        self.conn = pymysql.connect(host = 'mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com',
                            user = 'cs48_ajara',
                            password = 'ajara2019',
                            db = 'recipull_rds_db',
                            charset = 'utf8mb4',
                            cursorclass = pymysql.cursors.DictCursor)

    def getIngFromRecipe(self, rname):
        #recipeName = input("Enter a recipe name: ")
        try: 
            with self.conn.cursor() as cursor:
                sql = "SELECT recipes.recipe_id FROM recipes WHERE recipe_name =%s"
                cursor.execute(sql, rname)
                recipeId = cursor.fetchone()
                recipeId = int(recipeId['recipe_id'])
                #print(recipeId)
            with self.conn.cursor() as cursor:
                sql = "SELECT pivot.ingredient_id FROM pivot WHERE pivot.recipe_id = "+str(recipeId)+""
                cursor.execute(sql)
                currIngId = cursor.fetchone()
                ingIdList = []
                while currIngId is not None:
                    ingIdList.append(int(currIngId['ingredient_id']))
                    currIngId = cursor.fetchone()
                #print(ingIdList)
                #l = len(ingIdList)
                '''for i in ingIdList:
                    i = str(i)'''
                ingIdStr = "("
                for i in ingIdList:
                    ingIdStr = ingIdStr+str(i)+", "
                ingIdStr = ingIdStr[0:-2]
                ingIdStr = ingIdStr + ")"
            with self.conn.cursor() as cursor:
                sql = "SELECT ingredients.ingredient_name FROM ingredients WHERE ingredients.ingredient_id IN "+ingIdStr+""
                cursor.execute(sql)
                ingName = cursor.fetchall()
                for i in ingName:
                    print(i['ingredient_name'])
        finally: 
            self.conn.close()


'''def getConnection():

        conn = pymysql.connect(host = 'mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com',
                            user = 'cs48_ajara',
                            password = 'ajara2019',
                            db = 'recipull_rds_db',
                            charset = 'utf8mb4',
                            cursorclass = pymysql.cursors.DictCursor)

        return conn

conn = getConnection()

#print("connect successful!")



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
    main()'''