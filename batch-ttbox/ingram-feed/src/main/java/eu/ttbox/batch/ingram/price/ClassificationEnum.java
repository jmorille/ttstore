package eu.ttbox.batch.ingram.price;

public enum ClassificationEnum {

	C0("0"), // Produit à faible rotation
	C1("1"), // Produit à faible rotation
	C2("2"), // Produit à faible rotation
	C3("3"), // Produit à faible rotation
	C4("4"), // Produit à faible rotation
	C5("5"), // Produit à faible rotation
	C6("6"), // Produit à faible rotation
	C7("7"), // Produit à faible rotation
	C8("8"), // Produit à faible rotation
	C9("9"), // Produit à faible rotation
	A("A"), // Produit à fort volume de stock. Ingram Micro cherche à toujours avoir du stock sur ces produits.
	B("B"), // Produit à volume de vente moyen. Ingram Micro approvisionne régulièrement le stock de ces produits.
	C("C"), // Produit à rotation plus faible sur lesquels Ingram Micro cherche à avoir du stock
	D("D"), // Produit obsolète
	N("N"), // Nouveau produit
	S("S"), // Produit spécial stocké uniquement sur commande client. Aucun retour n'est accepté pour ces produits
	X("X"); // Produit commandable uniquement en livraison directe. Typiquement, il s'agit de licences ou de Top Config. En XML, ces produits ne peuvent être
			// commandés qu'avec le message OrderType message et non avec le message order classique.

	private final String code;

	private ClassificationEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
