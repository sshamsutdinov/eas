<template>
     <b-card no-body class="overflow-hidden mt-5 ml-5" style="width: 570px; height:350px" border-variant="dark" 
     header-bg-variant="white"
     header-border-variant="warning"
     footer-border-variant="warning"
     footer-bg-variant="white"
     >
     <template #header>
        <h5><i>Статья: {{article.title}}({{article.pages.pageStart}}-{{article.pages.pageEnd}})</i></h5>
    </template>
    <b-row no-gutters>  
    
      <b-col md="12">
        <b-card-body :title="`${article.author} | ${article.authorAbout} `" >
          <b-card-text style="height:140px">
            <p>{{article.annotation}}</p>
          </b-card-text>
        </b-card-body>
      </b-col>
    </b-row>
    <template #footer>
         <b-button :href="article.pdfMeta" target="_blank" variant="success"  size="lg">PDF версия статьи</b-button>
    </template>
  </b-card>
</template>


<script>
export default {
    props:{
      article:{
        type: Object,
        required: true
      }
    }
}
</script>>
