#!/usr/bin/env bash
# Genera el entregable final (PDF) a partir de informe/informe.md.
# Requisitos: Node.js. Usa md-to-pdf (se descarga con npx si no está instalado).
# Uso (desde cualquier carpeta):  bash informe/generar_pdf.sh
set -euo pipefail
cd "$(dirname "$0")/.."

SALIDA="Informe_TallerUML_UcabRecicla.pdf"
OPCIONES='{"format":"Letter","printBackground":true,"margin":{"top":"18mm","bottom":"18mm","left":"16mm","right":"16mm"}}'

if command -v md-to-pdf >/dev/null 2>&1; then MD2PDF=(md-to-pdf); else MD2PDF=(npx --yes md-to-pdf); fi

# md-to-pdf a veces se queda colgado en el primer arranque del navegador: se reintenta hasta 3 veces.
for intento in 1 2 3; do
  rm -f informe/informe.pdf
  if timeout 120 "${MD2PDF[@]}" informe/informe.md \
       --basedir . \
       --stylesheet informe/estilo.css \
       --pdf-options "$OPCIONES" && [ -s informe/informe.pdf ]; then
    mv informe/informe.pdf "$SALIDA"
    echo "PDF generado: $SALIDA"
    exit 0
  fi
  echo "Intento $intento fallido, reintentando..." >&2
done
echo "No se pudo generar el PDF." >&2
exit 1
