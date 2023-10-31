<template>
  <div class="recipe-upload">
    <h1>Upload Recipe</h1>
    <form @submit.prevent="submitRecipe">
      <div
        class="form-group"
        style="margin-bottom: 20px;"
      >
        <label for="name">Name</label>
        <br />
        <input
          type="text"
          id="name"
          v-model="recipe.name"
          class="form-control"
          required
        />
      </div>

      <!-- Implementation for Instructions -->
      <div class="instructions">
        <label for="instructions">Instructions</label>
        <div
          v-for="(instruction, index) in instructions"
          :key="index"
          class="form-group"
        >

          <!-- 'Edit' state for instructions -->
          <div
            v-if="instruction.edit"
            class="input-group mb-3"
          >
            <span class="input-group-text">
              Step {{ index + 1 }}:
            </span>
            <input
              type="text"
              v-model="instruction.text"
              class="form-control"
            />
            <div style="display: flex; justify-content: flex-end; gap: 5px;">
              <button
                @click="addEdit(index, 'instructions')"
                class="btn btn-primary"
              >
                Add
              </button>
              <button
                @click="remove(index, 'instructions')"
                class="btn btn-primary"
                :disabled="instructions.length === 1"
              >
                Remove
              </button>
            </div>
          </div>

          <!-- 'Display' state for instructions -->
          <div
            v-else
            style="background-color: #f5f5f5; padding: 3px; display: flex; justify-content: space-between; align-items: flex-start;"
          >
            <div style="flex-grow: 1; margin-right: 10px;">
              <span>Step {{ index + 1 }}: {{ instruction.text }}</span>
            </div>
            <div style="display: flex; justify-content: flex-end; gap: 5px;">
              <button
                @click="edit(index, 'instructions')"
                class="btn btn-primary"
              >
                Edit
              </button>
              <button
                @click="remove(index, 'instructions')"
                class="btn btn-primary"
                :disabled="instructions.length === 1"
              >
                Remove
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Implementation for Ingredients -->
      <div class="ingredients">
        <label for="ingredient">Ingredients</label>
        <div
          v-for="(ingredient, index) in ingredients"
          :key="'ingredient' + index"
          class="form-group"
        >
          <!-- 'Edit' state for ingredients -->
          <div
            v-if="ingredient.edit"
            class="input-group mb-3"
          >
            <span class="input-group-text">Name:</span>
            <input
              type="text"
              v-model="ingredient.name"
              class="form-control"
            />
            <span class="input-group-text">Quantity:</span>
            <input
              type="number"
              v-model="ingredient.quantity"
              class="form-control"
            />
            <span class="input-group-text">Unit:</span>
            <select
              v-model="ingredient.unit"
              class="form-select"
            >
              <option
                v-for="unit in allUnits"
                :key="unit"
                :value="unit"
              >
                {{ unit }}
              </option>
            </select>

            <div style="display: flex; justify-content: flex-end; gap: 5px;">
              <button
                @click="addEdit(index, 'ingredients')"
                class="btn btn-primary"
              >
                Add
              </button>
              <button
                @click="remove(index, 'ingredients')"
                class="btn btn-primary"
                :disabled="ingredients.length === 1"
              >
                Remove
              </button>
            </div>
          </div>

          <!-- 'Display' state for ingredients -->
          <div
            v-else
            style="background-color: #f5f5f5; padding: 3px; display: flex; justify-content: space-between; align-items: flex-start"
          >
            <div style="display: flex; flex-direction: row; align-items: center; gap: 20px;">
              <span>Name: <strong> {{ ingredient.name }}, {{ ingredient.quantity }} {{ ingredient.unit }} </strong></span>

            </div>
            <div style="display: flex; justify-content: flex-end; gap: 5px;">
              <button
                @click="edit(index, 'ingredients')"
                class="btn btn-primary"
              >
                Edit
              </button>
              <button
                @click="remove(index, 'ingredients')"
                class="btn btn-primary"
                :disabled="ingredients.length === 1"
              >
                Remove
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-between mb-3">
        <!-- Serving size div -->
        <div class="form-group">
          <label for="servings">Servings</label>
          <br />
          <select
            id="servings"
            v-model="recipe.servings"
            class="form-select"
          >
            <option
              v-for="number in 8"
              :value="number"
              :key="number"
            >{{ number }}</option>
          </select>
        </div>
        <!-- Meal Type div -->

        <!-- Meal Type div -->
        <div class="form-group">
          <label for="mealType">Meal Type</label>
          <br />
          <select
            id="mealType"
            v-model="recipe.mealType"
            class="form-select"
          >
            <option
              v-for="mealType in allMealTypes"
              :key="mealType"
            >{{ mealType }}</option>
          </select>
        </div>
        <!-- Full Meal div -->
        <div class="form-group">
          <label for="fullMeal">Full Meal</label>
          <br />
          <select
            id="fullMeal"
            v-model="recipe.fullMeal"
            class="form-select"
          >
            <option value="true">True</option>
            <option value="false">False</option>
          </select>
        </div>
        <!-- Time To Cook div -->
        <div class="form-group">
          <label for="timeToCook">Cooking Time (in minutes)</label>
          <br />
          <input
            type="number"
            id="timeToCook"
            v-model="recipe.timeToCook"
            class="form-control"
          />
        </div>
      </div>
      <!-- Comments div -->
      <div class="form-group">
        <label for="comments">Comments</label>
        <br />
        <textarea
          id="comments"
          v-model="recipe.comments"
          class="form-control"
          style="height: 100px;"
        ></textarea>
      </div>

      <div>
        <button
          type="submit"
          class="btn btn-primary"
          style="float: left"
        >
          Save Recipe
        </button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recipe: {
        name: "",
        instructions: [],
        ingredients: [],
        servings: 0,
        mealType: "",
        fullMeal: false,
        timeToCook: "",
        comments: "",
      },
      instructions: [{ text: "", edit: true }],
      ingredients: [{ name: "", quantity: "", unit: "", edit: true }],
      allMealTypes: [],
      allUnits: [],
    };
  },
  created() {
    this.fetchMealTypes();
    this.fetchUnits();
  },
  methods: {
    addEdit(index, property) {
      if (property === "instructions") {
        this.instructions[index].edit = false;
        if (index === this.instructions.length - 1) {
          this.instructions.push({ text: "", edit: true });
        }
      } else if (property === "ingredients") {
        this.ingredients[index].edit = false;
        if (index === this.ingredients.length - 1) {
          this.ingredients.push({
            name: "",
            quantity: "",
            unit: "",
            edit: true,
          });
        }
      }
    },
    edit(index, property) {
      if (property === "instructions") {
        this.instructions[index].edit = true;
      } else if (property === "ingredients") {
        this.ingredients[index].edit = true;
      }
    },
    remove(index, property) {
      if (property === "instructions" && this.instructions.length > 1) {
        this.instructions.splice(index, 1);
      } else if (property === "ingredients" && this.ingredients.length > 1) {
        this.ingredients.splice(index, 1);
      }
    },
    fetchMealTypes() {
      fetch("/api/mealtypes")
        .then((res) => res.json())
        .then((data) => {
          this.allMealTypes = data;
        });
    },
    fetchUnits() {
      fetch("/api/units")
        .then((res) => res.json())
        .then((data) => {
          this.allUnits = data;
        });
    },
    submitRecipe() {
      // Convert the first character of the recipe name to uppercase
      // and append the rest of the name starting from the second character
      this.recipe.name =
        this.recipe.name.trim().charAt(0).toUpperCase() +
        this.recipe.name.trim().slice(1);
      this.recipe.instructions = this.instructions
        // The 'filter' function is used to remove any instructions that are still in edit mode
        // This is determined by checking if the 'edit' property of the instruction object is false
        // The result is an array of instruction objects that are no longer in edit mode
        .filter((item) => !item.edit)
        // The 'map' function is used to transform each instruction object in the array
        // We transform the 'text' property of the instruction object to include the instruction number
        // The trim function is used to remove leading and trailing whitespace from the instruction text
        // Then we convert the first character to uppercase
        // and append the rest of the instruction text starting from the second character
        .map(
          (item, index) =>
            `${index + 1}. ${
              item.text.trim().charAt(0).toUpperCase() +
              item.text.trim().slice(1)
            }`
        );
      // Similar to the 'instructions', the 'filter' function is used to remove any ingredients that are still in edit mode
      this.recipe.ingredients = this.ingredients
        .filter((item) => !item.edit)
        .map((item) => ({
          ...item,
          // Convert the ingredient name to lowercase
          name: item.name.toLowerCase(),
        }));

      console.log(this.recipe);

      // Sending the recipe data to the server using a POST request
      fetch("/api/recipes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.recipe),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Success:", data);
          // Reset the recipe object
          this.recipe = {
            name: "",
            instructions: [],
            ingredients: [],
            servings: 0,
            mealType: "",
            fullMeal: false,
            timeToCook: "",
            comments: "",
          };
          // Reset the instructions array
          this.instructions = [{ text: "", edit: true }];
          // Reset the ingredients array
          this.ingredients = [{ name: "", quantity: "", unit: "", edit: true }];
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },
  },
};
</script>
