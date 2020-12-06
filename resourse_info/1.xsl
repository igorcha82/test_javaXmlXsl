<?xml version="1.0" encoding="utf-8"?>
  <xsl:template match="entries">
    <entries>
      <xsl:apply-templates/>
    </entries>
  </xsl:template>

  <xsl:template match="entry">
    <entry>
      <xsl:for-each select="*">
        <xsl:attribute name="{name()}">
          <xsl:value-of select="text()"/>
        </xsl:attribute>
      </xsl:for-each>
    </entry>
  </xsl:template>
</xsl:stylesheet>