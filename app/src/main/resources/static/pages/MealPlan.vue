<template>
    <div>
      <h1>Create this week's Meal Plan!</h1>
      <p v-if="currentDate">Today's date: {{ currentDate }}</p>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentDate: '',
      };
    },
    methods: {
      todayDate() {
        const today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const yyyy = today.getFullYear();
        const formattedDate = dd + '-' + mm + '-' + yyyy;
  
        this.currentDate = formattedDate;
        console.log(formattedDate);
      },
    },
    mounted() {
      this.todayDate();
    },
  };
  </script>