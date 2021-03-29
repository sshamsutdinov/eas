<template>
     <b-card no-body class="overflow-hidden mt-4  text-dark" style="width: 900px; height:470px" border-variant="dark" 
     header-bg-variant="white"
     header-border-variant="warning"
     footer-border-variant="warning"
     footer-bg-variant="white"
     >
    <template #header>
        <h3><i>Выпуск №{{issue.number}}</i></h3>
    </template>
    
    <b-row no-gutters>  
      <b-col md="8">
        <b-card-img :src="issue.coverMeta" alt="Image" class="rounded-0"  style="height:330px; width:600px"></b-card-img>
      </b-col>
      <b-col md="4">
        <b-card-body  class="ml-3">
          <b-card-text class="ml-3">
              <h3 class="text-left text-success  mt-5">{{issue.doi}}</h3>
               <h5 class="text-left text-muted  mt-2">{{issue.publishingDate}}</h5>
          </b-card-text>
            <b-button :href="issue.pdfMeta"  target="_blank" variant="warning"  class="mt-3 mr-4 " size="lg">PDF версия выпуска</b-button>
        </b-card-body>
      </b-col>
    </b-row>
    <template #footer>
         <b-button :to="{name: 'articles', params:{id: issue.number}}" variant="success"  size="lg">Подробнее о выпуске</b-button>
        
    </template>
  </b-card>
</template>

<script>
export default {
    props:{
        issue:{
            type: Object,
            required: true
        }
    }
}
</script>