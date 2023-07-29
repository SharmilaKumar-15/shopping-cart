function replaceTable(tableElement){
	div_tag = document.querySelector('#content-body > div:nth-child(2)');
	old_table = document.querySelector('#items');
	div_tag.removeChild(old_table);
	div_tag.appendChild(tableElement);
}

let search_button = document.getElementById('search');
	search_button.addEventListener('click',function(){
		searchItem();
	}
