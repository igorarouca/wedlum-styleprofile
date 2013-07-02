var wedlum = wedlum||{};
wedlum.tag = wedlum.tag||{};

wedlum.tag.TagModelParser = function(){};
wedlum.tag.TagModelParser.prototype = {
    parse: function(source){
        return jsyaml.load(source);
    },
    pathGivenLine: function(source, lineNumber){
        var result = new Array();
        var stack = source.split('\n');
        stack.splice(lineNumber + 1);
        if (stack.length == 0) return "";

        var current = "";
        var currentIndent = this.getIndentLevel(stack.pop());
        while (current = stack.pop()){
            var indent = this.getIndentLevel(current);
            if (indent < currentIndent){
                result.unshift(current.substr(0, current.indexOf(':')).trim());
                currentIndent = indent;
            }
        }
        result.unshift('');
        return  result.join('/');
    },
    getIndentLevel: function (current) {
        return (current.match(/^\s+/) || [''])[0].length;
    }
};
