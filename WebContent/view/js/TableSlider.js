function TableSlider(url){
	if(this instanceof Window)
		return new TableSlider();
		
	var trigger = "",
		target = "",
		form = "",
		dataArray = "",
		rowHeight = "",
		tbodyFirstChild = "tbody tr:first-child",
		firstRow = "",
		isRunning = false,
		commentUrl = url;
	
	this.setTrigger = function(id){
		trigger = $(id);
		trigger.on("click", this.slide);
		return trigger;
	}
	
	this.setTargetTable = function(id){
		target = $(id);
		getFirstRow();
		return this;
	}
	
	var getFirstRow = function(){
		firstRow = target.find(tbodyFirstChild);
		if(firstRow != undefined)
			rowHeight = firstRow.outerHeight();
		
		else
			rowHeight = 83;
	}
	
	this.slide = function(event){
		event.preventDefault();
		
		if(isRunning){return;}
		
		isRunning = true;
		
		form = trigger.closest("form");
		dataArray = form.serializeArray();
		isValid();
		
		if(!emptyFieldIn(dataArray)){doSlideAnimation();}
		
		else
			isRunning = false;
	}
	
	var doSlideAnimation = function(){
		clearInputFields();
		resetLabelColors();
		
		getRow().then(function(result){
			firstRow = $(tbodyFirstChild, result);
			hideRow();
			insertNewRowInToTable();
			fadeInRow();
		});
	}
	
	var resetLabelColors = function(){form.find("span").css("color", "red");}
	
	var insertNewRowInToTable = function(){
			target.find("tbody").prepend(firstRow);
	}
	
	var hideRow = function(){
		firstRow.css({
			overflow: "hidden",
			height: 0,
			opacity: 0});
	}
	
	var fadeInRow = function(){
		firstRow.animate({
			height: rowHeight,
			opacity: 1},
			function(){isRunning = false;});		
	}
	
	var getRow = function(){
		var deferred = $.Deferred();
		$.post(commentUrl, dataArray, deferred.resolve);
		return deferred.promise();
	}
	
	var emptyFieldIn = function(array){
		var hasEmptyField = false;
		
		$.each(array, function(index, field){
			if(field.value.length < 1)
				hasEmptyField = true;
		});
		
		return hasEmptyField;
	}
	
	var isValid = function(){
		if(!trigger || !target || !url)
			throw "Both target,trigger and url must be set!";
			
		else if(!dataArray)
			throw "No data provided";
	}
	
	var clearInputFields = function(){
		form.children("input:nth-of-type(-n+3)").val("");
		form.children("textarea").val("");
	}
}