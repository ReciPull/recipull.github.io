import dbConnection

def main():
    #try:
    while True:
        d = dbConnection.dbConnection()
        print("R: get recipe names from ingredient")
        print("I: get ingredient names from recipe")
        print("Q: quit program")
        c = input("Enter command: ")
        if(c == "R" or c == "r"):
            r = input("Enter ingredient name: ")
            d.getRecipeFromIng(i)
        if(c == "I" or c == "i"):
            i = input("Enter recipe name: ")
            d.getIngFromRecipe(r)
        if(c == "Q" or c == "q"):
            break

if __name__ == "__main__":
    main()