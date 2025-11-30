package pnm.tigad.addnavigation.model

data class OrderUiState(

    val entree: MenuItem.EntreeItem? = null,
    val sideDish: MenuItem.SideDishItem? = null,
    val accompainment: MenuItem.AccompanimentItem? = null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)