<template>
  <div class="recipe-upload">
    <h1>Upload Recipe</h1>
    <form @submit.prevent="submitRecipe">
      <div class="form-group">
        <label for="name">Name</label>
        <br />
        <input type="text" id="name" v-model="recipe.name" required />
      </div>

      <!-- Implementation for Instructions -->
      <div class="instructions">
        <h2>Instructions</h2>
        <div v-for="(instruction, index) in instructions" :key="index">
          <p
            v-if="instruction.edit"
            style="background-color: #f5f5f5; padding: 10px"
          >
            Step {{ index + 1 }}:
            <input type="text" v-model="instruction.text" style="width: 60%" />
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
          </p>
          <p v-else style="background-color: #f5f5f5; padding: 10px">
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
        >
          <p
            v-if="ingredient.edit"
            style="background-color: #f5f5f5; padding: 10px"
          >
            Name:
            <input type="text" v-model="ingredient.name" style="width: 30%" />
            Quantity:
            <input
              type="number"
              v-model="ingredient.quantity"
              style="width: 8%"
            />
            Unit:
            <input type="text" v-model="ingredient.unit" style="width: 10%" />
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
          </p>
          <p v-else style="background-color: #f5f5f5; padding: 10px">
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
      <!-- Serving size div -->
      <div class="form-group">
        <label for="servings">Servings</label>
        <br />
        <select id="servings" v-model="recipe.servings" required>
          <option v-for="number in 8" :value="number" :key="number">
            {{ number }}
          </option>
        </select>
      </div>

      <!-- Meal Type div -->
      <div class="form-group">
        <label for="mealType">Meal Type</label>
        <br />
        <select id="mealType" v-model="recipe.mealType" required>
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
        <select id="fullMeal" v-model="recipe.fullMeal" required>
          <option value="true">True</option>
          <option value="false">False</option>
        </select>
      </div>

      <!-- Calories div -->
      <div class="form-group">
        <label for="calories">Calories</label>
        <br />
        <input type="number" id="calories" v-model="recipe.calories" required />
      </div>

      <!-- Time To Cook div -->
      <div class="form-group">
        <label for="timeToCook">Time To Cook</label>
        <br />
        <input
          type="number"
          id="timeToCook"
          v-model="recipe.timeToCook"
          required
        />
      </div>

      <!-- Comments div -->
      <div class="form-group">
        <label for="comments">Comments</label>
        <br />
        <textarea id="comments" v-model="recipe.comments" required />
      </div>

      <div>
        <button type="submit" class="btn btn-primary" style="float: left">
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
        calories: 0,
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

<!-- <template>
  <div class="recipe-upload">
    <h1>Upload Recipe</h1>
    <form @submit.prevent="submitRecipe">
      <div class="form-group">
        <label for="name">Name</label>
        <br />
        <input type="text" id="name" v-model="recipe.name" required />
      </div>

   
      <div class="instructions">
        <h2>Instructions</h2>
        <div v-for="(instruction, index) in instructions" :key="index">
          <p
            v-if="instruction.edit"
            style="background-color: #f5f5f5; padding: 10px"
          >
            Step {{ index + 1 }}:
            <input type="text" v-model="instruction.text" style="width: 70%" />
            <button
              @click="addEdit(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Add
            </button>
            <button
              @click="remove(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Remove
            </button>
          </p>
          <p v-else style="background-color: #f5f5f5; padding: 10px">
            Step {{ index + 1 }}: {{ instruction.text }}
            <button
              @click="edit(index, 'instructions')"
              class="btn btn-primary"
            >
              Edit
            </button>
            <button
              @click="remove(index, 'instructions')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Remove
            </button>
          </p>
        </div>
      </div>

     
      <div class="ingredients">
        <h2>Ingredients</h2>
        <div
          v-for="(ingredient, index) in ingredients"
          :key="'ingredient' + index"
        >
          <p
            v-if="ingredient.edit"
            style="background-color: #f5f5f5; padding: 10px"
          >
            Name:
            <input type="text" v-model="ingredient.name" style="width: 30%" />
            Quantity:
            <input
              type="number"
              v-model="ingredient.quantity"
              style="width: 20%"
            />
            Unit:
            <input type="text" v-model="ingredient.unit" style="width: 20%" />
            <button
              @click="addEdit(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Add
            </button>
            <button
              @click="remove(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Remove
            </button>
          </p>
          <p v-else style="background-color: #f5f5f5; padding: 10px">
            Name: {{ ingredient.name }} Quantity:
            {{ ingredient.quantity }} Unit: {{ ingredient.unit }}
            <button @click="edit(index, 'ingredients')" class="btn btn-primary">
              Edit
            </button>
            <button
              @click="remove(index, 'ingredients')"
              class="btn btn-primary"
              style="margin-left: 5px"
            >
              Remove
            </button>
          </p>
        </div>
      </div>
      <div>
        <button type="submit" class="btn btn-primary" style="float: left">
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
        calories: 0,
        timeToCook: "",
        comments: "",
      },
      instructions: [{ text: "", edit: true }],
      ingredients: [{ name: "", quantity: "", unit: "", edit: true }],
    };
  },
  methods: {
    add(property) {
      if (property === "instructions") {
        this.instructions.push({ text: "", edit: true });
      } else if (property === "ingredients") {
        this.ingredients.push({ name: "", quantity: "", unit: "", edit: true });
      }
    },
    edit(index, property) {
      if (property === "instructions") {
        this.instructions[index].edit = true;
      } else if (property === "ingredients") {
        this.ingredients[index].edit = true;
      }
    },
    addEdit(index, property) {
      if (property === "instructions") {
        this.instructions[index].edit = false;
        this.add("instructions");
      } else if (property === "ingredients") {
        this.ingredients[index].edit = false;
        this.add("ingredients");
      }
    },
    remove(index, property) {
      if (property === "instructions") {
        this.instructions.splice(index, 1);
      } else if (property === "ingredients") {
        this.ingredients.splice(index, 1);
      }
    },
    submitRecipe() {
      // Here, you can process the final object to be sent to the backend
      console.log(this.recipe);
    },
  },
};
</script> -->

<!-- <template>
  <div class="recipe-upload">
    <h1>Upload Recipe</h1>
    <form @submit.prevent="submitRecipe">
      <div class="form-group">
        <label for="name">Name</label>
        <br />
        <input type="text" id="name" v-model="recipe.name" required />
      </div>

      

      <div class="instructions">
        <h2>Instructions</h2>
        <div v-for="(instruction, index) in instructions" :key="index">
          <p
            v-if="instruction.edit"
            style="background-color: #f5f5f5; padding: 10px"
          >
            Step {{ index + 1 }}:
            <input type="text" v-model="instruction.text" style="width: 70%" />
            <button
              @click="addEditInstruction(index)"
              class="btn btn-primary"
              style="float: right; margin-left: 5px"
            >
              Add
            </button>
            <button
              @click="removeInstruction(index)"
              class="btn btn-primary"
              style="float: right"
            >
              Remove
            </button>
          </p>
          <p v-else style="background-color: #f5f5f5; padding: 10px">
            Step {{ index + 1 }}: {{ instruction.text }}
            <button
              @click="editInstruction(index)"
              class="btn btn-primary"
              style="float: right; margin-left: 5px"
            >
              Edit
            </button>
            <button
              @click="removeInstruction(index)"
              class="btn btn-primary"
              style="float: right"
            >
              Remove
            </button>
          </p>
        </div>
        <button
          @click.prevent="addStep"
          class="btn btn-primary"
          style="float: right"
        >
          Add Step
        </button>
        <br />
        <button type="submit" class="btn btn-primary" style="float: left">
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
        instructions: "",
        ingredients: [],
        servings: 0,
        mealType: "",
        fullMeal: false,
        calories: 0,
        timeToCook: "",
        comments: "",
      },
      newInstruction: "",
      newIngredient: { name: "", quantity: 0, unit: "" },
      instructions: [{ text: "", edit: true }],
      ingredients: [],
    };
  },
  methods: {
    addStep() {
      this.instructions.push({ text: "", edit: true });
    },
    editInstruction(index) {
      this.instructions[index].edit = true;
    },
    addEditInstruction(index) {
      this.instructions[index].edit = false;
      this.addStep();
    },
    removeInstruction(index) {
      this.instructions.splice(index, 1);
    },
    // similar methods for ingredients
    submitRecipe() {
      // Here, you can process the final object to be sent to the backend
      console.log(this.recipe);
    },
  },
};
</script> -->
