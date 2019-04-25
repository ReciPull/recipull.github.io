/* Alexis Cole and Rhianna So */

SELECT recipes.recipe_name
FROM recipes
WHERE
    (recipes.ingredient1 = ingredients.pk) /* where ingredients.pk is the name of the primary key connected to the foreign keys */
    OR (recipes.ingredient2 = ingredients.pk)
    OR (recipes.ingredient3 = ingredients.pk)
    OR (recipes.ingredient4 = ingredients.pk)
    OR (recipes.ingredient5 = ingredients.pk)
    OR (recipes.ingredient6 = ingredients.pk)
    OR (recipes.ingredient7 = ingredients.pk)
    OR (recipes.ingredient8 = ingredients.pk)
    OR (recipes.ingredient9 = ingredients.pk)
    OR (recipes.ingredient10 = ingredients.pk);