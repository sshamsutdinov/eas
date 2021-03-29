<template>
   <div>
  <b-jumbotron>
    <template #header>      
          Wellcome to admin room       
    </template>

    

    <hr class="my-4">

    <router-link to="/dungeon/users" class="btn btn-lg btn-outline-secondary my-0 mr-4 mb-4">
               <img src="@/assets/Users.svg" alt="UserList"><img>          
             Web-site users
    </router-link>
    <router-link to="/dungeon/issues-catalog" class="btn btn-lg btn-outline-secondary my-0  mb-4 d-inline-block ml-4">
             <img src="@/assets/IssuesAndArticles.svg" alt="UserList"><img> 
             Issues and articles
    </router-link>
  </b-jumbotron>
</div>
</template>

<script>
export default {
   
}
</script>