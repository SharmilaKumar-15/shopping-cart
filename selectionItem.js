function ItemFunction(array){

	this.contains=function(str){
		return array.filter(function(ele){
			return ele[1].includes(str);
		});
	}

	this.notContains=function(str){
		return array.filter(function(ele){
			return !ele[1].includes(str);
		});
	}

	this.equals=function(str){
		return array.filter(function(ele){
			return ele[1]==str;
		});
	}

	this.notEquals=function(str){
		return array.filter(function(ele){
			return ele[1]!=str;
		});
	}

}


function quantityFunction(array){
	this.equals=function(val){
		return array.filter(function(ele){
			return ele[2]==val;
		});
	}

	this.notEquals=function(val){
		return array.filter(function(ele){
			return ele[2]!=val;
		});
	}

	this.gEquals=function(val){
		return array.filter(function(ele){
			return ele[2]>val;
		});
	}

	this.lEquals=function(val){
		return array.filter(function(ele){
			return ele[2]<ele;
		});
	}

	this.between=function(val1,val2){
		return array.filter(function(ele){
			a=(val1<val2)?val1:val2;
			b=(val1>val2)?val1:val2;

			return (a<=ele[2]) && (b>=ele[2]);
		})
	}
}

function priceFunction(array){
	this.equals=function(val){
		return array.filter(function(ele){
			return ele[2]==val;
		});
	}

	this.notEquals=function(val){
		return array.filter(function(ele){
			return ele[2]!=val;
		});
	}

	this.gEquals=function(val){
		return array.filter(function(ele){
			return ele[2]>val;
		});
	}

	this.lEquals=function(val){
		return array.filter(function(ele){
			return ele[2]<ele;
		});
	}

	this.between=function(val1,val2){
		return array.filter(function(ele){
			a=(val1<val2)?val1:val2;
			b=(val1>val2)?val1:val2;

			return (a<=ele[2]) && (b>=ele[2]);
		})
	}
}


function dateFunction(array){
	this.equals=function(val){
		return array.filter(function(ele){
			return ele[4]==val;
		});
	}

	this.before=function(val){
		return array.filter(function(ele){
			return ele[4]<val;
		});
	}

	this.after=function(val){
		return array.filter(function(ele){
			return ele[4]>val;
		});
	}
}




// a=[[1,'rice',10,8,'2022-09-08'],[2,'wheat',20,8,'2022-10-08']]