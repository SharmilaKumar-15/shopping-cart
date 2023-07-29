function replaceTable(tableElement){
	div_tag = document.querySelector('#content-body > div:nth-child(2)');
	old_table = document.querySelector('#items');
	div_tag.removeChild(old_table);
	div_tag.appendChild(tableElement);
}

function searchItem(itemname, quantity, price, date){
	const status=[]

	status[0] = itemname.value!='select';
	status[1] = quantity.value!='select';
	status[2] = price.value!='select';
	status[3] = date.value!='select';
	arr = array;

	for(i=0;i<4;i++){
	
		if(status[i]){
			switch(i){
				case 0:
					query = '#full-header > div:nth-child(3) > input[type=text]:nth-child(2)';
					input_tag = document.querySelector(query);
					value = input_tag.value;
					if(value==''){
						alert('Must Fill Item Name!');
						return;
					}else{
						itemObj = new ItemFunction(arr);
						choice = itemname.value;
						switch(choice){
							case 'Equals':
								arr = itemObj.equals(value);
								break;
							case 'Not Equals':
								arr = itemObj.notEquals(value);
								break;
							case 'Contains':
								arr = itemObj.contains(value);
								break;
							case 'Not Contains':
								arr = itemObj.notContains(value);
								break;
						}
					}
					break;

				case 1:
					query1 = '#full-header > div:nth-child(4) > input[type=number]:nth-child(2)';
					input_tag = document.querySelector(query1);
					value1= input_tag.value;
					query2 = '#full-header > div:nth-child(4) > input[type=number]:nth-child(3)';
					input_tag = document.querySelector(query2);
					value2 = input_tag.value;
					if(value1=='' || (quantity.value=='Between' && (value1=='' || value2==''))){
						alert('Must Fill Quantity');
						return;
					}else{
						quantityObj = new quantityFunction(arr);
						choice = quantity.value;
						switch(choice){
							case 'Equals':
								arr = quantityObj.equals(value1);
								break;
							case 'Not Equals':
								arr = quantityObj.notEquals(value1);
								break;
							case 'Greater Than':
								arr = quantityObj.gEquals(value1);
								break;
							case 'Lesser Than':
								arr = quantityObj.lEquals(value1);
								break;
							case 'Between':
								arr = quantityObj.between(value1, value2);
								break;
						}
					}
					break;

				case 2:
					query1 = '#full-header > div:nth-child(5) > input[type=number]:nth-child(2)';
					input_tag = document.querySelector(query1);
					value1= input_tag.value;
					query2 = '#full-header > div:nth-child(5) > input[type=number]:nth-child(3)';
					input_tag = document.querySelector(query2);
					value2 = input_tag.value;
					if(value1=='' || (quantity.value=='Between' && (value1=='' || value2==''))){
						alert('Must Fill Price');
						return;
					}else{
						priceObj = new priceFunction(arr);
						choice = price.value;
						switch(choice){
							case 'Equals':
								arr = priceObj.equals(value1);
								break;
							case 'Not Equals':
								arr = priceObj.notEquals(value1);
								break;
							case 'Greater Than':
								arr = priceObj.gEquals(value1);
								break;
							case 'Lesser Than':
								arr = priceObj.lEquals(value1);
								break;
							case 'Between':
								arr = priceObj.between(value1, value2);
								break;
						}
					}
					break;

				case 3:
					query = '#full-header > div:nth-child(6) > input[type=date]:nth-child(2)';
					input_tag=document.querySelector(query);
					value = input_tag.value;
					if(value==''){
						alert('Must Fill date!');
					}else{
						dateObj = new dateFunction(arr);
						choice = date.value;
						switch(choice){
							case 'Equals':
								arr = dateObj.equals(value);
								break;
							case 'Before':
								arr = dateObj.before(value);
								break;
							case 'After':
								arr = dateObj.after(value);
								break;
						}
					}
					break;
			}
		}
	}

	replaceTable(array_to_table(arr));
}


function init(){


	let itemname = document.getElementById('itemname');
	itemname.addEventListener('change',function(){
		if (itemname.value=='select'){
			query = '#full-header > div:nth-child(3) > input[type=text]:nth-child(2)';
			input_tag=document.querySelector(query);
			input_tag.style.visibility='hidden';			
		}
		else{
			query = '#full-header > div:nth-child(3) > input[type=text]:nth-child(2)';
			input_tag=document.querySelector(query);
			input_tag.style.visibility='visible';
			input_tag.placeholder='Item Name';
		}
	},false);


	let quantity = document.getElementById('quantity');
	quantity.addEventListener('change',function(){
		if(quantity.value=='select'){
			query='#full-header > div:nth-child(4) > input[type=number]:nth-child(2)';
			input_tag=document.querySelector(query);
			input_tag.style.visibility='hidden';
			query = '#full-header > div:nth-child(4) > input[type=number]:nth-child(3)';
			input_tag=document.querySelector(query);
			input_tag.style.visibility='hidden';			
		
		}
		else{
			if(quantity.value=='Between'){
				query='#full-header > div:nth-child(4) > input[type=number]:nth-child(3)';
				input_tag=document.querySelector(query);
				input_tag.style.visibility='visible';
				input_tag.placeholder='Quantity'
			}else{
				query = '#full-header > div:nth-child(4) > input[type=number]:nth-child(3)';
				input_tag=document.querySelector(query);
				input_tag.style.visibility='hidden';	
			}
			query = '#full-header > div:nth-child(4) > input[type=number]:nth-child(2)';
			input_tag=document.querySelector(query);
			input_tag.style.visibility='visible';
			input_tag.placeholder='Quantity';
		}
	},false);

	let price = document.getElementById('price');
	price.addEventListener('change',function(){
		if(price.value=='select'){
			query='#full-header > div:nth-child(5) > input[type=number]:nth-child(2)';
			input_tag=document.querySelector(query)
			input_tag.style.visibility='hidden';
			query = '#full-header > div:nth-child(5) > input[type=number]:nth-child(3)';
			input_tag=document.querySelector(query)
			input_tag.style.visibility='hidden';
		}else{
			if(price.value=='Between'){
				query='#full-header > div:nth-child(5) > input[type=number]:nth-child(3)';
				input_tag=document.querySelector(query)
				input_tag.style.visibility='visible';
				input_tag.placeholder='Price';
			}else{
				query = '#full-header > div:nth-child(5) > input[type=number]:nth-child(3)';
				input_tag=document.querySelector(query)
				input_tag.style.visibility='hidden';
			}
			query = '#full-header > div:nth-child(5) > input[type=number]:nth-child(2)';
			input_tag=document.querySelector(query)
			input_tag.style.visibility='visible';
			input_tag.placeholder='Price';
		}
	},false);

	let date = document.getElementById('date');
	date.addEventListener('change',function(){
		if(date.value=='select'){
			query = '#full-header > div:nth-child(6) > input[type=date]:nth-child(2)';
			input_tag=document.querySelector(query)
			input_tag.style.visibility='hidden';
		}
		else{
			query = '#full-header > div:nth-child(6) > input[type=date]:nth-child(2)';
			input_tag=document.querySelector(query)
			input_tag.style.visibility='visible';
		}
	});

	let search_button = document.getElementById('search');
	search_button.addEventListener('click',function(){
		searchItem(itemname, quantity, price, date);
	},true);
}

init();