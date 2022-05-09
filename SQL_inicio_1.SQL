CREATE TABLE "factura_autoimpresor" (
	"idfactura_autoimpresor" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"condicion" TEXT NOT NULL ,
	"forma_pago" TEXT NOT NULL ,
	"moneda_pago" TEXT NOT NULL ,
	"estado" TEXT NOT NULL ,
	"cod_establecimiento" INTEGER NOT NULL ,
	"punto_expedicion" INTEGER NOT NULL ,
	"numeracion_secuencial" INTEGER NOT NULL ,
	"total_pagar" NUMERIC(20,2) NOT NULL ,
	"total_exenta" NUMERIC(20,2) NOT NULL ,
	"total_gravada_5" NUMERIC(20,2) NOT NULL ,
	"total_gravada_10" NUMERIC(20,2) NOT NULL ,
	"liquidacion_iva_5" NUMERIC(20,2) NOT NULL ,
	"liquidacion_iva_10" NUMERIC(20,2) NOT NULL ,
	"cuenta_5" TEXT NOT NULL ,
	"cuenta_10" TEXT NOT NULL ,
	"cuenta_exenta" TEXT NOT NULL ,
	"total_item" INTEGER NOT NULL ,
	"total_articulo" INTEGER NOT NULL ,
	"monto_letra" TEXT NOT NULL ,
	"observacion" TEXT NOT NULL ,
	"fk_idtimbrado" INTEGER NOT NULL ,
	"fk_idcliente" INT NOT NULL ,
	"fk_idcajero" INTEGER NOT NULL ,
	"fk_idusuario" INT NOT NULL ,
	PRIMARY KEY("idfactura_autoimpresor")
);
CREATE TABLE "empresa" (
	"idempresa" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"razon_social" TEXT NOT NULL ,
	"ruc" INTEGER NOT NULL ,
	"dv" INTEGER NOT NULL ,
	"direccion" TEXT NOT NULL ,
	"celular" TEXT NOT NULL ,
	"activo" BOOLEAN NOT NULL ,
	"mensaje_final" TEXT NOT NULL ,
	PRIMARY KEY("idempresa")
);
CREATE TABLE "item_timbrado" (
	"iditem_timbrado" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"fk_idempresa" INTEGER NOT NULL ,
	"fk_idtimbrado" INTEGER NOT NULL ,
	PRIMARY KEY("iditem_timbrado")
);
CREATE TABLE "timbrado" (
	"idtimbrado" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"mac_pc" TEXT NOT NULL ,
	"numero" INTEGER NOT NULL ,
	"fecha_inicio" DATE NOT NULL ,
	"fecha_fin" DATE NOT NULL ,
	"cod_establecimiento" INTEGER NOT NULL ,
	"punto_expedicion" INTEGER NOT NULL ,
	"numero_inicial" INTEGER NOT NULL ,
	"numero_final" INTEGER NOT NULL ,
	"numero_actual" INTEGER NOT NULL ,
	"activo" BOOLEAN NOT NULL ,
	"es_vencido" BOOLEAN NOT NULL ,
	"numero_limite" INTEGER NOT NULL ,
	"dias_limite" INTEGER NOT NULL ,
	"numero_caja" INTEGER NOT NULL ,
	PRIMARY KEY("idtimbrado")
);
CREATE TABLE "item_factura_auto" (
	"iditem_factura_auto" SERIAL NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"descripcion" TEXT NOT NULL ,
	"cantidad" INTEGER NOT NULL ,
	"precio_venta" NUMERIC(20,2) NOT NULL ,
	"precio_compra" NUMERIC(20,2) NOT NULL ,
	"iva" INTEGER NOT NULL ,
	"total_exenta" NUMERIC(20,2) NOT NULL ,
	"total_gravada_5" NUMERIC(20,2) NOT NULL ,
	"total_gravada_10" NUMERIC(20,2) NOT NULL ,
	"cuenta_exenta" TEXT NOT NULL ,
	"cuenta_5" TEXT NOT NULL ,
	"cuenta_10" TEXT NOT NULL ,
	"fk_idfactura_autoimpresor" INTEGER NOT NULL ,
	"fk_idproducto" INT NOT NULL ,
	PRIMARY KEY("iditem_factura_auto")
);
CREATE TABLE "producto_cuenta" (
	"idproducto_cuenta" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"descripcion" TEXT NOT NULL ,
	"iva" INTEGER NOT NULL ,
	"activo" BOOLEAN NOT NULL ,
	PRIMARY KEY("idproducto_cuenta")
);
CREATE TABLE "cajero" (
	"idcajero" INTEGER NOT NULL ,
	"fecha_creado" TIMESTAMP NOT NULL ,
	"creado_por" TEXT NOT NULL ,
	"nombre" TEXT NOT NULL ,
	"cedula" TEXT NOT NULL ,
	"direccion" TEXT NOT NULL ,
	"telefono" TEXT NOT NULL ,
	"activo" BOOLEAN NOT NULL ,
	PRIMARY KEY("idcajero")
);
ALTER TABLE producto ADD COLUMN iva INTEGER DEFAULT 10;
ALTER TABLE producto ADD COLUMN fk_idproducto_cuenta INTEGER DEFAULT 1;
ALTER TABLE usuario ADD COLUMN fk_idcajero INTEGER DEFAULT 1;
