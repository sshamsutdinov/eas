<template>
    <div class="mt-5 pt-5">
    <b-table striped hover :items="this.getAllIssueArticles" :fields="fields" >
         <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>
        <template #cell(keyword)="data">
        {{ data.item.keywords[0] }},   {{data.item.keywords[1]}}
      </template>
           <template #cell(delete)="data">
            <b-button :to="data.index" class="btn-md btn-danger">Delete</b-button>
        </template>
        <template #cell(update)="data">
            <b-button :to="data.index" class="btn-md btn-warning">Update</b-button>
        </template>
    </b-table>
  </div>
</template>

<script>
import {mapGetters, mapMutations} from 'vuex';
export default {
     mounted(){
                 this.getAllArticlesById(this.$route.params.id);            
                   
                },
        computed: mapGetters(['getAllIssueArticles']),
        methods:{
            ...mapMutations(['getAllArticlesById'])
        },
        data(){
            return{
                fields: [
                    {key: 'index', label:'Article №'},
                    {key: 'title', label:'Article\'s title'},
                    {key: 'author', label:'Author'},
                    {key: 'rubric', label:'Article\'s rubric'},
                     {key: 'keyword', label:'Article\'s keywords'},
                    {key: 'pdfMeta', label:'Article\'s pdf'},
                      { key: 'delete', label:  '' },
                    { key: 'update', label:  '' }
                  
                ],
            }
        }
}
</script>