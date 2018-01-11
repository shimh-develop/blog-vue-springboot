<template>
    <mavon-editor :placeholder=placeholder
				:toolbarsFlag=toolbarsFlag
				:subfield=subfield
				:default_open=default_open
				:value=value></mavon-editor>
</template>


<script>

import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
    name: 'MarkdownEditor',
    props: {
    	placeholder:String,
    	toolbarsFlag:Boolean,
    	subfield:Boolean,
    	default_open:String,
    	value:String
    },
    components: {
        mavonEditor
    }
}
</script>
<style scoped>

</style>
