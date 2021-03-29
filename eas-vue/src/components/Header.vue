<template>
    <header>
        <b-navbar toggleable="lg" class="fixed-top p-1" type="dark" variant="dark">
            <b-navbar-brand to="/">
                <img src="@/assets/Logo.svg" alt=""/>
            </b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item to="/" class="btn btn-sm btn-outline-success" ><b>Домой</b></b-nav-item> || 
                <b-nav-item to="/aboutus" class="btn btn-sm btn-outline-warning ml-2"><b>О журнале</b></b-nav-item> || 
                <b-nav-item href="#"  v-b-toggle.sidebar-backdrop class="btn btn-sm btn-outline-info ml-2"><b>Навигация</b></b-nav-item>
            </b-navbar-nav>   
                    
            <b-navbar-nav class="ml-auto">
                  <b-nav-form >
                     <b-form-input size="md" class="mr-sm-2" placeholder="Search" v-model="searchString"></b-form-input>
                     <b-button :to="{name:'articlesBySearch', params:{searchString: searchString}}" class="my-2 my-sm-0 mr-3 btn-info">Search</b-button>
                </b-nav-form>
                <b-nav-item-dropdown right>
                <template #button-content>
                    <img src="@/assets/open-door.svg" alt=""/> 
                </template>                
                <b-dropdown-item-button href="/" class="btn btn-outline-dark">
                     <img src="@/assets/Sign in.svg" alt="" border="0" height="25" width="25" style="max-width: 130px;"/>
                      Sign in                    
                </b-dropdown-item-button>
                 <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item-button href="#" class="btn btn-outline-dark">
                    <img src="@/assets/Sign up.svg" alt="" border="0" height="25" width="25" style="max-width: 130px;"/>
                    Sign up
                </b-dropdown-item-button>               
                </b-nav-item-dropdown>           
            </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </header>
</template>

<script>
    export default {
        data(){
            return{
                searchString: ''
            }
        }
    }
</script>
