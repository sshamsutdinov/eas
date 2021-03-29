<template>
     <b-container fluid class="pt-5">
          <b-row class="justify-content-around">
            <IssueCard
                v-for="issue in getIssues"
                :key="issue.number"
                v-bind:issue="issue"
            />
                
          </b-row> 
    </b-container>  
</template>

<script>
 import {mapGetters} from 'vuex';
import IssueCard from '@/components/IssueCard.vue'
export default {
    components:{
        IssueCard
    },
    computed:mapGetters(['getIssues'])
}
</script>