var wedlum = wedlum||{};
wedlum.tag = wedlum.tag||{};

wedlum.tag.TagModelParser = function(){};
wedlum.tag.TagModelParser.prototype = {
    preProcess: function(input) {
        var result = '';
        var lines = input.split('\n');
        var contextStack = new Array();
        contextStack.push(0);
        for(var i =0; i< lines.length; i++){
            var indentation = String(lines[i].match(/^\s+/)||'');
            var indentationLevel = indentation.length;
            console.debug(indentationLevel);
            var peek = contextStack[contextStack.length -1];
            if (indentationLevel == peek){
                result += '\n' + lines[i];
                continue;
            }
            if (indentationLevel > peek){
                contextStack.push(indentationLevel);
                result += '\n#INDENT{' + lines[i];
            } else {
                result += '\n';
                while(indentationLevel < contextStack[contextStack.length -1]){
                    contextStack.pop();
                    result += '#DEDENT}';
                }
                result += lines[i];
            }
        }

        while(contextStack.pop() != 0){
            result += '#DEDENT}';
        }
        return result;
    }
};
