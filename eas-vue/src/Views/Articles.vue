<template>
    <b-container fluid class="pt-5">
    <b-container fluid  v-if="this.$route.params.id != undefind && this.$route.params.id != null "> 
         <h1 class="mt-3 pb-1">Статьи журнала | Выпуск №{{this.$route.params.id}}</h1>
       <hr style="height:2px;border-width:0;color:gray;background-color:green" class="mb-0">
        <b-row class="justify-content-start">      
        <ArticleCard
            v-for="article in this.getAllIssueArticles"
            :key="article.title"
             v-bind:article="article"
        />
       
        </b-row>
    </b-container>
    <b-container fluid v-else>
         <h1 class="mt-3 pb-1">Статьи журнала всех выпусков</h1>
       <hr style="height:2px;border-width:0;color:gray;background-color:green" class="mb-0">
        <b-row class="justify-content-start">      
        <ArticleCard
            v-for="article in this.getAllArticles"
            :key="article.title"
             v-bind:article="article"
        />
        </b-row>
    </b-container>
    </b-container>  
</template>


<script>
    import ArticleCard from '@/components/ArticleCard.vue';
    import {mapGetters, mapMutations} from 'vuex';
    export default {
          created(){
                  
                  
                  if(this.$route.params.id == undefined && this.$route.params.searchString == undefined)
                  {
                       this.GetAllArticles();
                  }
                  else{
                      if(this.$route.params.id != undefined) this.getAllArticlesById(this.$route.params.id);
                      else this.GetArticlesBySearchString(this.$route.params.searchString);
                 }                         
                },
                 computed: mapGetters(['getAllIssueArticles','getAllArticles']),
        methods:{
            ...mapMutations(['getAllArticlesById', 'GetAllArticles', 'GetArticlesBySearchString'])
        },
        components:{
            ArticleCard
        }   
               
    }
</script>
