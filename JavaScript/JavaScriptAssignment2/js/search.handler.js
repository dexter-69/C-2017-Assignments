let users = [
  'Table - 1',
  'Table - 2',
  'Table - 3',
  'Table - 4',
];

let menuList = [
  'Burger',
  'Idli',
  'Noodle',
  'Pizza',
];

let initialiseMenuItems = () => {
  let items_storage = JSON.parse('{"item_data":[]}');
  let firstItem = {"item_name" : "Burger", "item_price" : 100, "course_type" : "fast food"};
  let secondItem = {"item_name" : "Idli", "item_price" : 50, "course_type" : "main course"};
  let thirdItem = {"item_name" : "Coke", "item_price" : 40, "course_type" : "beverage"};
  let fourthItem = {"item_name" : "Pepsi", "item_price" : 40, "course_type" : "beverage"};
  let fifthItem = {"item_name" : "Biryani", "item_price" : 180, "course_type" : "main course"};
  let sixthItem = {"item_name" : "Ice-Cream", "item_price" : 100, "course_type" : "desert"};

  items_storage['item_data'].push(firstItem);
  items_storage['item_data'].push(secondItem);
  items_storage['item_data'].push(thirdItem);
  items_storage['item_data'].push(fourthItem); 
  items_storage['item_data'].push(fifthItem);
  items_storage['item_data'].push(sixthItem);

  localStorage.setItem('menu_list', JSON.stringify(items_storage));
}

let initialiseTables = () => {
  let tables_storage = JSON.parse('{"table_data":[]}');
  let items = JSON.parse('{"items_data": []}');
  let firstTable = {"table_num" : "1", "total_items" : 0, "total_bill" : 0, "items" : items};
  let secondTable = {"table_num" : "2", "total_items" : 0, "total_bill" : 0, "items" : items};
  let thirdTable = {"table_num" : "3", "total_items" : 0, "total_bill" : 0, "items" : items};
  let fourthTable = {"table_num" : "4", "total_items" : 0, "total_bill" : 0, "items" : items};

  tables_storage['table_data'].push(firstTable);
  tables_storage['table_data'].push(secondTable);
  tables_storage['table_data'].push(thirdTable);
  tables_storage['table_data'].push(fourthTable);

  localStorage.setItem('table_list', JSON.stringify(tables_storage));
}


function initialiseTablesAndMenu(updateMenuItemFlag, updateTableFlag) {
  if(null == localStorage.getItem('menu_list')) {
    initialiseMenuItems();
    console.log("Menu Initialised");
  }

  if(null == localStorage.getItem('table_list') || updateTableFlag) {
    initialiseTables();
    console.log("Table Initialised");
  }

  render_tables((JSON.parse(localStorage.getItem("table_list")))['table_data']);
  render_menu_lists((JSON.parse(localStorage.getItem("menu_list")))["item_data"]);
}

let render_tables = function(lists){
  let li = "";
  for(index in lists){
    li += "<li class='table_div'  ondrop='drop(event)' ondragover='allowDrop(event) '><div class='card'><div class='card-body' <h5 class='card-title'>Table - " + lists[index].table_num + "</h5><br/><br/><p>Rs. " + lists[index].total_bill + " | Total Items: " + lists[index].total_items + "</p></div></div></li><br>";
  }
  if(lists.length == 0) li = "<center> <h6> No Results Found </h6></center>";
  document.getElementById("table-list-ul").innerHTML = li;
}

let render_menu_lists = function(lists){
  let li = "";
  for(index in lists){
    li += "<li class='menu_list_div' draggable='true' ondragstart='drag(event)'><div class='card'><div class='card-body' <h5 class='card-title'>" + lists[index].item_name + "</h5><br/><br/><p>"+lists[index].item_price+"</p><div></div></li><br>";
  }
  if(lists.length == 0) li = "<center> <h6> No Results Found </h6></center>";

  document.getElementById("menu-list").innerHTML = li;
}



// lets filters it
input = document.getElementById('filter_tables');
menuInput = document.getElementById('filter_menu_items');

let filterTables = function(event){
  keyword = "" + input.value;
  let jsonObject = JSON.parse(localStorage.getItem("table_list"));
  let tableDataArray = jsonObject["table_data"];
  filtered_tables = tableDataArray.filter(function(table){
      let tableNum = (table.table_num);
      return tableNum.indexOf(keyword) > -1;
  });
  
  render_tables(filtered_tables);
}

let filterMenu = function(event){
  keyword = menuInput.value.toLowerCase();
  let jsonObject = JSON.parse(localStorage.getItem("menu_list"));
  let menuDataArray = jsonObject["item_data"];
  filtered_menu_items = menuDataArray.filter(function(menuItem){
      let itemName = menuItem.item_name
      itemName = itemName.toLowerCase();
      let courseType = menuItem.course_type;
      courseType = courseType.toLowerCase();
      return itemName.indexOf(keyword) > -1 || courseType.indexOf(keyword) > -1
  });
  
  render_menu_lists(filtered_menu_items);
}

input.addEventListener('keyup', filterTables);
menuInput.addEventListener('keyup', filterMenu);

function pickData(srcIndex) {
  localStorage.setItem('src_index', "" + srcIndex);
}

function addItemToTable(destIndex) {
  let srcIndex = +(localStorage.getItem('src_index'));

  let tableJsonObject = JSON.parse(localStorage.getItem('table_list'));
  let menuItemJsonObject = JSON.parse(localStorage.getItem('menu_list'));

  let tableDataArray = tableJsonObject['table_data'];
  let menuDataArray = menuItemJsonObject['item_data'];

  let currentTable = tableDataArray[destIndex];
  let draggedItem = menuDataArray[srcIndex];

  

  let found = false;
  let foundIndex = 0;
  let currentTableItems = currentTable.items['items_data'];
  for(let i = 0; i < currentTableItems.length; i++) {
    if(draggedItem.item_name == currentTableItems[i].item_name) {
      found = true;
      foundIndex = i;
      break;
    }
  }
  if(found) {
    currentTableItems[foundIndex].servings += 1;
    currentTable.total_bill += draggedItem.item_price;
  }
  else {
    let tempItem = {"item_name" : draggedItem.item_name, "servings" : 1, 'item_price' : draggedItem.item_price};
    currentTableItems.push(tempItem);
    currentTable.total_items += 1;
    currentTable.total_bill += draggedItem.item_price;
  }

  currentTable.items['item_data'] = currentTableItems;
  tableDataArray[destIndex] = currentTable;
  tableJsonObject['table_data'] = tableDataArray;
  localStorage.setItem('table_list', JSON.stringify(tableJsonObject));
  render_tables(tableDataArray);
}

function showTableData(index) {
  let jsonObject = JSON.parse(localStorage.getItem('table_list'));

  let currentTable = jsonObject['table_data'][index];
  let currentTableItems = currentTable.items['items_data'];
  let tr = "";
  
  for(let i = 0; i < currentTableItems.length; i++) {
    let servingsInput = "<form action='#'><div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style='width: 100%;'><input class='mdl-textfield__input' type='number' placeholder='e.g.1' onKeyUp='updateServings("+ (i) +", "+ index +", this)' value='"+(currentTableItems[i].servings)+"'></div></form>";
    tr += "<tr><th scope='row'>" + (i + 1) + "</th><td>" + currentTableItems[i].item_name +"</td><td>"+ currentTableItems[i].item_price +"</td><td>" + servingsInput + "</td><i class='material-icons' onclick='removeItemFromTable("+ (i) +", "+ index +")'>delete</i></tr>";
  }
  tr += "<tr><th scope='row'></th><td></td><td>Total: <br/> " + currentTable.total_bill + "</td><td></td><td></td></tr>";
  document.getElementById("table_body").innerHTML = tr;
}

function remove(array, index) {
    if (index !== -1) {
        array.splice(index, 1);
    }
} 

function removeItemFromTable(index, currentTableIndex) {
  let jsonObject = JSON.parse(localStorage.getItem('table_list'));
  let tableDataArray = jsonObject['table_data'];
  let currentTable = jsonObject['table_data'][currentTableIndex];
  let currentTableItems = currentTable.items['items_data'];
  currentTable.total_bill -= (currentTableItems[index].item_price * currentTableItems[index].servings);
  currentTable.total_items -= 1;
  remove(currentTableItems, index);
  currentTable.items['item_data'] = currentTableItems;
  tableDataArray[currentTableIndex] = currentTable;
  jsonObject['table_data'] = tableDataArray;
  localStorage.setItem('table_list', JSON.stringify(jsonObject));
  render_tables(tableDataArray);
  showTableData(currentTableIndex);
}

function finishSession(index) {
  let jsonObject = JSON.parse(localStorage.getItem('table_list'));
  let tableDataArray = jsonObject['table_data'];
  let currentTable = jsonObject['table_data'][index];
  let currentTableItems = currentTable.items['items_data'];
  alert("Total Bill is: " + currentTable.total_bill);
  currentTable.total_bill = 0;
  currentTable.total_items = 0;
  let items = JSON.parse('{"items_data": []}');
  currentTable.items = items;
  tableDataArray[index] = currentTable;
  jsonObject['table_data'] = tableDataArray;
  localStorage.setItem('table_list', JSON.stringify(jsonObject));
  render_tables(tableDataArray);
}

function updateServings(itemIndex, tableIndex, input) {
  let jsonObject = JSON.parse(localStorage.getItem('table_list'));
  let tableDataArray = jsonObject['table_data'];
  let currentTable = jsonObject['table_data'][tableIndex];
  let currentTableItems = currentTable.items['items_data'];

  let newServing = input.value;
  if(newServing <= 0) {
    alert("Please Enter Serving Greater > 0");
    return;
  }
  console.log(newServing);
  input.value = newServing;
  let oldServing = currentTableItems[itemIndex].servings;
  currentTable.total_bill = currentTable.total_bill - (currentTableItems[itemIndex].item_price * oldServing) + (currentTableItems[itemIndex].item_price * newServing);
  currentTableItems[itemIndex].servings = newServing;
  currentTable.items['item_data'] = currentTableItems;
  tableDataArray[tableIndex] = currentTable;
  jsonObject['table_data'] = tableDataArray;
  localStorage.setItem('table_list', JSON.stringify(jsonObject));
  render_tables(tableDataArray);
  showTableData(tableIndex);
}