package com.es.nf.commons.services.genetic;

import com.es.nf.domain.v1.BiologicalFile;
import com.es.nf.domain.v1.genetic.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GeneticServicesImpl implements GeneticServices {

    @Override
    public void createChild(BiologicalFile pBFFather,
                            BiologicalFile pBFMother,
                            BiologicalFile pBFChild, Genome pGenome) {

        // Set Parents in Biological file
        setParent(pBFFather, pBFMother, pBFChild);

        //Create empty genetic code for child
        createEmpyGeneticCode(pBFChild);

        // Set Parents in Biological file
        mixGenes(pBFFather, pBFMother, pBFChild, pGenome);

    }

    private void setParent(BiologicalFile pBFFather, BiologicalFile pBFMother, BiologicalFile pBFChild) {
        pBFChild.setPartyIdPere(pBFFather.getPartyId());
        pBFChild.setPartyIdMere(pBFMother.getPartyId());
    }

    private void createEmpyGeneticCode(BiologicalFile pBFChild) {

        if (pBFChild.getGeneticCode() == null) {
            pBFChild.setGeneticCode(new GeneticCodeImpl());
        }
    }

    private void mixGenes(BiologicalFile pBFFather, BiologicalFile pBFMother, BiologicalFile pBFChild, Genome pGenome) {

        for (GeneInformation geneInfo : pGenome.getTemplate().values()) {
            Gene gene = new GeneImpl();

            gene.setName(geneInfo.getGeneName());

            Gene geneF = pBFFather.getGeneticCode().getGene(geneInfo.getGeneName(), pGenome);
            Gene geneM = pBFMother.getGeneticCode().getGene(geneInfo.getGeneName(), pGenome);

            gene.setAlleleA(selectAllele(geneF));
            gene.setAlleleB(selectAllele(geneM));

            pBFChild.getGeneticCode().addGene(gene, pGenome);

        }
    }

    private int selectAllele(Gene pGene) {
        if (Math.random() < 0.5) {
            return pGene.getAlleleA();
        } else {
            return pGene.getAlleleB();
        }
    }

    @Override
    public void visitGenealogy(GenealogyVisitor pVisitor) {
        // 1- we visit the colons to apply the action
        List<BiologicalFile> bfList = pVisitor.getAllBFListPerPartyId();

        // Visit all nodes
        visitGeneration(bfList, pVisitor);
    }

    private void visitGeneration(List<BiologicalFile> pBFList, GenealogyVisitor pVisitor) {

        for (BiologicalFile bf : pBFList) {
            pVisitor.doVisit(bf);
        }

    }

}
