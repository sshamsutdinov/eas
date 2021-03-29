<template>
     <b-sidebar
      id="sidebar-backdrop"
      title="Навигационное меню"
      backdrop
      sidebar-class="border-right border-dark"
      shadow
    >
     
        <b-nav vertical class="w-100">
    <b-nav-item to="/issues" class="btn btn-sm btn-outline-success mt-0 m-1" ><h4 class="text-dark">ISSUES</h4></b-nav-item>
    <b-nav-item :to="{name:'articles'}" class="btn btn-sm btn-outline-success mt-0 m-1" ><h4 class="text-dark">Articles</h4></b-nav-item>
   <b-nav-item to="/" class="btn btn-sm btn-outline-success mt-2 m-1" ><h4 class="text-dark">FEES</h4></b-nav-item>

    <b-dropdown
    text="SUBMIT"
    block
    size="lg"
    variant="outline-primary"
    class="m-1 mt-2 "
    menu-class="w-100"
  >
    <b-dropdown-item class="text-center" href="#"><h5>Author Guidelines</h5></b-dropdown-item>
     <b-dropdown-divider></b-dropdown-divider>
    <b-dropdown-item class="text-center" to="/articles"><h5>Submission side</h5></b-dropdown-item>
     <b-dropdown-divider></b-dropdown-divider>
    <b-dropdown-item class="text-center" href="#"><h5>Open Access Policy</h5></b-dropdown-item>
     <b-dropdown-divider></b-dropdown-divider>
    <b-dropdown-item class="text-center" href="#"><h5>Purchase</h5></b-dropdown-item>
  </b-dropdown>

  </b-nav>
    </b-sidebar>
</template>

<script>
    export default {
    }
</script>
