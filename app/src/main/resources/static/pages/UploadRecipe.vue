<template>
  <div class="recipe-upload">
    <h1>Upload Recipe</h1>
    <form @submit.prevent="submitRecipe">
      <div class="form-group">
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
        <h2>Instructions</h2>
        <div
          v-for="(instruction, index) in instructions"
          :key="index"
          class="form-group"
        >
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

            <button
              @click="addEdit(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
            >
              Add
            </button>
            <button
              @click="remove(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
              :disabled="instructions.length === 1"
            >
              Remove
            </button>
          </div>
          <p
            v-else
            style="background-color: #f5f5f5; padding: 10px"
          >
            Step {{ index + 1 }}: {{ instruction.text }}
            <button
              @click="edit(index, 'instructions')"
              class="btn btn-primary"
              style="float: right; margin-left: 5px"
            >
              Edit
            </button>
            <button
              @click="remove(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
              :disabled="instructions.length === 1"
            >
              Remove
            </button>
          </p>
        </div>
      </div>

      <!-- Implementation for Ingredients -->
      <div class="ingredients">
        <h2>Ingredients</h2>
        <div
          v-for="(ingredient, index) in ingredients"
          :key="'ingredient' + index"
          class="form-group"
        >

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
            <input
              type="text"
              v-model="ingredient.unit"
              class="form-control"
            />

            <button
              @click="addEdit(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
            >
              Add
            </button>
            <button
              @click="remove(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
              :disabled="ingredients.length === 1"
            >
              Remove
            </button>
          </div>
          <p
            v-else
            style="background-color: #f5f5f5; padding: 10px"
          >
            Name: {{ ingredient.name }} Quantity:
            {{ ingredient.quantity }} Unit: {{ ingredient.unit }}
            <button
              @click="edit(index, 'ingredients')"
              class="btn btn-primary"
              style="float: right; margin-left: 5px"
            >
              Edit
            </button>
            <button
              @click="remove(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px; float: right"
              :disabled="ingredients.length === 1"
            >
              Remove
            </button>
          </p>
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
            required
          >
            <option
              v-for="number in 8"
              :value="number"
              :key="number"
            >{{ number }}</option>
          </select>
        </div>
        <!-- Meal Type div -->
        <div class="form-group">
          <label for="mealType">Meal Type</label>
          <br />
          <select
            id="mealType"
            v-model="recipe.mealType"
            class="form-select"
            required
          >
            <option value="Hovedret">Hovedret</option>
            <option value="Forret">Forret</option>
            <option value="Dessert">Dessert</option>
            <option value="Salat">Salat</option>
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
            required
          >
            <option value="true">True</option>
            <option value="false">False</option>
          </select>
        </div>
        <!-- Time To Cook div -->
        <div class="form-group">
          <label for="timeToCook">Time To Cook (in minutes)</label>
          <br />
          <input
            type="number"
            id="timeToCook"
            v-model="recipe.timeToCook"
            class="form-control"
            required
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
          required
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
    };
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
    submitRecipe() {
      console.log(this.recipe);
    },
  },
};
</script>
