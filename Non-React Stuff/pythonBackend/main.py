import dbConnection

def main():
    #try:
    d = dbConnection.dbConnection()
    r = input("Enter recipe name: ")
    d.getIngFromRecipe(r)

if __name__ == "__main__":
    main()