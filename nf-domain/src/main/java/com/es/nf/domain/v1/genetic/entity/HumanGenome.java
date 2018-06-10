package com.es.nf.domain.v1.genetic.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.es.nf.domain.v1.genetic.exception.GeneticException;
import org.yaml.snakeyaml.Yaml;

public class HumanGenome extends GenomeImpl {

    public HumanGenome() {
        super();
        init();
    }

    public void init() {
        try {
            ArrayList<String> config = (ArrayList<String>) loadConfig();
            initGenome(config);
        } catch (IOException e) {
           throw new GeneticException("Cannot load the HumanGenome config file");
        }
    }

    public List<String> loadConfig() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("genetic.yaml");
        return new Yaml().loadAs(is, ArrayList.class);
    }

    public void initGenome(List<String> values) {
        Map<String, GeneInformation> mp = new HashMap<String,GeneInformation>();

        for (String gene : values) {
            GeneInformation gi = new GeneInformation();

            String[] genevalue  = gene.split(";");

            gi.setGeneName(genevalue[0]);
            gi.setShortDesc(genevalue[1]);
            gi.setLongDesc(genevalue[2]);
            gi.setChromosomeName(genevalue[3]);
            gi.setFirstBit(Integer.valueOf(genevalue[4]).intValue());
            gi.setLastBit(Integer.valueOf(genevalue[5]).intValue());

            this.getTemplate().putIfAbsent(gi.getGeneName(), gi);
        }

    }
}
