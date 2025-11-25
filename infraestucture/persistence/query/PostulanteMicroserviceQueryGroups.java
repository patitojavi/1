package es.altia.bne.postulante.infraestucture.persistence.query;

import es.altia.bne.common.db.QueryGroup;
import lombok.Getter;

public enum PostulanteMicroserviceQueryGroups implements QueryGroup {

    POSTULANNTE("postulante"),
    CURRICULAR("datosCurriculares");

    /**
     * Returns the element code.
     *
     * @return {@link java.lang.String} The element code.
     */
    @Getter
    private final String groupName;

    private PostulanteMicroserviceQueryGroups(final String groupName) {
        this.groupName = groupName;
    }
}
