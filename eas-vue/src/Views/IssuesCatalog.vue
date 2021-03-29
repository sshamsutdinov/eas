<template>
    <div class="mt-5 pt-5">
    <b-table striped hover no-border-collapse  :items="getAllIssues" :fields="fields" >
         <template #cell(count)="data">
         {{Object.keys(data.item.articles).length}}
        </template>
            <template #cell(delete)="data">
            <b-button @click:="DeleteIssue" :data-issueId="data.item.number" class="btn-md btn-danger">Delete</b-button>
        </template>
        <template #cell(update)="data">
            <b-button :to="data.item.number" class="btn-md btn-warning">Update</b-button>
        </template>
      <template #cell(details)="data">
         <b-button :to="{name: 'articleCatalog', params:{id: data.item.number}}" class="btn-md btn-info">Details</b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex';
export default {
        async mounted() {
            this.fetchIssues();
        },
        computed: mapGetters(['getAllIssues']),
        methods:{
            ...mapActions(['fetchDeleteIssue', 'fetchIssues']),
            DeleteIssue(event) {
                const issueId = event.currentTarger.getAttribute("data-issueId");
                this.fetchDeleteIssue(issueId);
                this.fetchIssues()
            }
        },
        data(){
            return{
                fields: [
                    {key: 'number', label:'Issue №'},
                    {key: 'doi', label:'Issue\'s DOI'},
                    {key: 'publishingDate', sortable:true, label:'Publishing date'},
                    {key: 'pdfMeta', label:'PDF issue\'s version'},
                    { key: 'count', label:  'Issues\' article count' },
                    { key: 'delete', label:  '' },
                    { key: 'update', label:  '' },
                    { key: 'details', label:  '' }
                ],
            }
        }
}
</script>