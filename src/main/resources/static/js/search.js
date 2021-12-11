const inputSearch = document.querySelector("#input-search");

const rowData = [].slice.call(document.querySelector(".table-list").children);
rowData.shift();

function handleSearch(){
	const searchKey = inputSearch.value
	console.log(searchKey);
	for (let i = 0; i < rowData.length; i++) {
		const rowText = rowData[i].innerText.replaceAll("\n", "");
		if (!rowText.includes(searchKey)) {
			rowData[i].style.display = "none";
		} else {
			rowData[i].style.display = "flex";
		}
	}
}

