<template>
    <div>
    <Jumbotron/>
    <Carousel/>
    </div>
</template>



<script>
import Jumbotron from '@/components/Jumbotron.vue';
import Carousel from '@/components/Carousel.vue';
import { mapActions } from 'vuex'
    export default {
      components: {
            Carousel,
            Jumbotron
        },
        methods: {
            ...mapActions(['fetchIssues'])
        },
        async mounted() {
            this.fetchIssues();
        }
    }
</script>
