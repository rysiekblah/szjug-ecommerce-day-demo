class ProductCtrl

    constructor: (@$log, @$location, @ProductService) ->
        @$log.debug "ProductCtrl created-"
        @products = {}
        this.productsList()

    productsList: () ->
      @ProductService.products().then(
        (data) =>
          @$log.debug "List: " + JSON.stringify(data.data)
          @products = data.data
      )

controllersModule.controller('ProductCtrl', ['$log', '$location', 'ProductService', ProductCtrl])
