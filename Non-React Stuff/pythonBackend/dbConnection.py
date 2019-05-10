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
        try: 
            with self.conn.cursor() as cursor:
                sql = "SELECT recipes.recipe_id FROM recipes WHERE recipe_name =%s"
                cursor.execute(sql, rname)
                recipeId = cursor.fetchone()
                recipeId = int(recipeId['recipe_id'])
            with self.conn.cursor() as cursor:
                sql = "SELECT pivot.ingredient_id FROM pivot WHERE pivot.recipe_id = "+str(recipeId)+""
                cursor.execute(sql)
                currIngId = cursor.fetchone()
                ingIdList = []
                while currIngId is not None:
                    ingIdList.append(int(currIngId['ingredient_id']))
                    currIngId = cursor.fetchone()
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
    
    def getRecipeFromIng(self,iname):
        try: 
            with self.conn.cursor() as cursor:
                sql = "SELECT ingredients.ingredient_id FROM ingredients WHERE ingredient_name =%s"
                cursor.execute(sql, iname)
                ingId = cursor.fetchone()
                ingId = int(ingId['ingredient_id'])
            with self.conn.cursor() as cursor:
                sql = "SELECT pivot.recipe_id FROM pivot WHERE pivot.ingredient_id = "+str(ingId)+""
                cursor.execute(sql)
                currRecId = cursor.fetchone()
                recIdList = []
                while currRecId is not None:
                    recIdList.append(int(currRecId['recipe_id']))
                    currRecId = cursor.fetchone()
                recIdStr = "("
                for i in recIdList:
                    recIdStr = recIdStr+str(i)+", "
                recIdStr = recIdStr[0:-2]
                recIdStr = recIdStr + ")"
            with self.conn.cursor() as cursor:
                sql = "SELECT recipes.recipe_name FROM recipes WHERE recipes.recipe_id IN "+recIdStr+""
                cursor.execute(sql)
                recName = cursor.fetchall()
                for i in recName:
                    print(i['recipe_name'])
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