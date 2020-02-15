$(document).ready(function () {

    // show list of product on first load
    showProducts();
    // when a 'read products' button was clicked
    $(document).on('click', '.read-products-button', function () {
        showProducts();
    });
});

// function to show list of products
function showProducts() {

    // get list of products from the API
    $.getJSON("http://restservices496.herokuapp.com/containers", function (data) {

        // html for listing products
        var read_products_html = `
            <!-- when clicked, it will load the create product form -->
            <div id='create-product' class='btn btn-primary pull-right m-b-15px create-product-button'>
                <span class='glyphicon glyphicon-plus'></span> Create Product
            </div>
            !-- start table -->
            <table class='table table-bordered table-hover'>
            
                <!-- creating our table heading -->
                <tr>
                    <th class='w-25-pct'>Name</th>
                    <th class='w-10-pct'>Type</th>
                    <th class='w-15-pct'>Longitude</th>
                    <th class='w-15-pct'>Latitude</th>
                    <th class='w-15-pct'>Address</th>
                    <th class='w-15-pct'>Weight</th>
                    <th class='w-25-pct text-align-center'>Action</th>
                </tr>`;

        // loop through returned list of data
        $.each(data.records, function (key, val) {

            // creating new table row per record
            read_products_html += `
        <tr>
 
            <td>` + val.name + `</td>
            <td>$` + val.type + `</td>
            <td>` + val.lng + `</td>
            <td>` + val.lat + `</td>
            <td>` + val.address + `</td>
            <td>` + val.weight + `</td>
            <!-- 'action' buttons -->
            <td>
                <!-- read product button -->
                <button class='btn btn-primary m-r-10px read-one-product-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-eye-open'></span> Read
                </button>
 
                <!-- edit button -->
                <button class='btn btn-info m-r-10px update-product-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-edit'></span> Edit
                </button>
 
                <!-- delete button -->
                <button class='btn btn-danger delete-product-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-remove'></span> Delete
                </button>
            </td>
 
        </tr>`;
        });

        // end table
        read_products_html += `</table>`;

        // inject to 'page-content' of our app
        $("#page-content").html(read_products_html);
        // chage page title
        changePageTitle("Read Products");

    });

}