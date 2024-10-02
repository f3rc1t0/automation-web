Feature: Product - Store
  @AutoStore
  @MyStore
  Scenario: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "professor.roy@gmail.com" y clave "CONtraSEnha"
    When navego a la categoria "Clothes" y subcategoria "Men"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

  @AutoStore
  @MyStore1
  Scenario: Login erróneo
    Given estoy en la página de la tienda
    And me logueo con mi usuario "professor.roy@gmail.com" y clave "CONtraSEnha1"

  @AutoStore
  @MyStore2
  Scenario: Categoria errónea
    Given estoy en la página de la tienda
    And me logueo con mi usuario "professor.roy@gmail.com" y clave "CONtraSEnha"
    When navego a la categoria "Autos" y subcategoria "Men"