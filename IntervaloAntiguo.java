class IntervaloAntiguo {

	private double inferior;

	private double superior;

	public IntervaloAntiguo(double inferior, double superior) {
		this.inferior = inferior;
		this.superior = superior;
	}

	public IntervaloAntiguo(double superior) {
		this(0, superior);
	}

	public IntervaloAntiguo(IntervaloAntiguo intervalo) {
		this(intervalo.inferior, intervalo.superior);
	}

	public IntervaloAntiguo() {
		this(0, 0);
	}

	public IntervaloAntiguo clone() {
		return new IntervaloAntiguo(this);
	}

	public double longitud() {
		return superior - inferior;
	}

	public void desplazar(double desplazamiento) {
		inferior += desplazamiento;
		superior += desplazamiento;
	}

	public IntervaloAntiguo desplazado(double desplazamiento) {
		IntervaloAntiguo intervalo = this.clone();
		intervalo.desplazar(desplazamiento);
		return intervalo;
	}

	public boolean incluye(double valor) {
		return inferior <= valor && valor <= superior;
	}

	public boolean incluye(IntervaloAntiguo intervalo) {
		assert intervalo!=null;
		return this.incluye(intervalo.inferior) && 
				this.incluye(intervalo.superior);
	}

	public boolean equals(IntervaloAntiguo intervalo) {
		assert intervalo!=null;
		return inferior == intervalo.inferior && 
				superior == intervalo.superior;
	}

	public IntervaloAntiguo interseccion(IntervaloAntiguo intervalo) {
		assert this.intersecta(intervalo);
		if (this.incluye(intervalo)){
			return intervalo.clone();
		} else if (intervalo.incluye(this)){
			return this.clone();
		} else if (this.incluye(intervalo.inferior)){
			return new IntervaloAntiguo(intervalo.inferior, superior);
		} else {
			return new IntervaloAntiguo(inferior, intervalo.superior);
		}
	}

	public boolean intersecta(IntervaloAntiguo intervalo) {
		assert intervalo!=null;
		return this.incluye(intervalo.inferior) ||
				this.incluye(intervalo.superior) || 
				intervalo.incluye(this);
	}

	public void oponer() {
		double inferiorInicial = inferior;
		double superiorInicial = superior;
		inferior = -superiorInicial;
		superior = -inferiorInicial;
	}

	public void doblar() {
		double longitudInicial = this.longitud();
		inferior -= longitudInicial / 2;
		superior += longitudInicial / 2;
	}

	public void recoger() {
		GestorIO gestorIO = new GestorIO();
		gestorIO.out("Inferior?");
		inferior = gestorIO.inDouble();
		gestorIO.out("Superior?");
		superior = gestorIO.inDouble();
	}

	public void mostrar() {
		new GestorIO().out("[" + inferior + "," + superior + "]");
	}

	public IntervaloAntiguo[] trocear(int trozos) {
		return null;
	}
	
	public static void main(String[] args){
		IntervaloAntiguo intervalo = new IntervaloAntiguo();
		intervalo.recoger();
		intervalo.mostrar();
		new GestorIO().out("Longitud: "+intervalo.longitud());
	}

}
