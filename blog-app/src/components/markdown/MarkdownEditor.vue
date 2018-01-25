<template>
    <mavon-editor class="me-editor" ref="md" v-model="mark.value" v-bind="mark"></mavon-editor>
</template>


<script>

import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
    name: 'MarkdownEditor',
    props: {
    	mark:Object
    },
    data (){
    	return {
    	}
    },
    mounted() {
    	this.$set( this.mark, 'ref', this.$refs.md )
    },
    components: {
        mavonEditor
    }
}
</script>
<style scoped>
.me-editor {
	z-index: 6!important;
}	
</style>
