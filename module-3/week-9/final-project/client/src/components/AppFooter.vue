<template>
    <footer id="app-footer">
      <div>&copy; 2025 Scheduling, Inc.</div>
      <div>
        <a href="/about.html">About</a>
        &nbsp;|&nbsp;
        <a href="mailto:support@example.com">Contact Us</a>
      </div>
    </footer>
  </template>
  
  <script>

</script>

<style scoped>
 #app-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    grid-template-columns: 3fr 50px minmax(150px, 1fr);
    background-color: rgb(136, 231, 150);
    padding: 10px;
    border: 2px solid rgb(0, 117, 41);
    border-radius: 5px;
  }

</style>