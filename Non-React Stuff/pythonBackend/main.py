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
            i = input("Enter ingredient name: ")
            d.getRecipeFromIng(i)
        if(c == "I" or c == "i"):
            r = input("Enter recipe name: ")
            d.getIngFromRecipe(r)
        if(c == "Q" or c == "q"):
            break

if __name__ == "__main__":
    main()