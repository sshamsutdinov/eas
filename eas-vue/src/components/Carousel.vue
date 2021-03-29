<template>
    <div id="carousel"> 
     <b-carousel
      v-model="slideId"
      :interval="3000"
      indicators
      background="#ababab"
      style="text-shadow: 1px 1px 2px #333; "
       border-variant="secondary"
    >    
        <b-carousel-slide  v-for="issue in this.getIssues"
                            :key="issue.number"
                            v-bind:issue="issue">  
        <template #img >
          <img
            class="d-block w-100"
            width="1024"
            height="690"
            :src="issue.coverMeta"
            alt="image slot"
          >
        </template>
             <b-button :to="`/articles/${issue.number}`" variant="primary"  size="lg">Подробнее о выпуске</b-button>    
      </b-carousel-slide>
         
    </b-carousel>
    </div>
</template>


<script>
 import {mapGetters, mapMutations} from 'vuex';
    export default {
       
        data(){
            return{
                slideId: 0
            }
        },
        computed: mapGetters(['getIssues']),
         methods:{
            ...mapMutations(['getIssuesForCarousel'])
        },
         mounted(){
          this.getIssuesForCarousel();
        
        }
    }
</script>

<style scoped>
    #carousel{
        margin-top: 0px;
        margin-left: 195px;
        max-width: 80%;
        max-height: 60%;
        border-style: outset double;
    }   
</style>