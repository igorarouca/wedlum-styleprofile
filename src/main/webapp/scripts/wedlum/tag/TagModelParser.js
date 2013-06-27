var wedlum = wedlum||{};
wedlum.tag = wedlum.tag||{};

wedlum.tag.TagModelParser = function(){};
wedlum.tag.TagModelParser.prototype = {
    parse: function(source){
        return jsyaml.load(source);
    }
};
