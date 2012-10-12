/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2012, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.metamodel.spi.binding;

import java.util.List;

/**
 * Common information pertaining to the binding of the various plural attribute natures (one-to-many, basic, etc).
 *
 * @author Steve Ebersole
 */
public interface PluralAttributeElementBinding {
	/**
	 * Retrieves the plural attribute binding descriptor whose element binding is described here.
	 *
	 * @return The plural attribute binding descriptor.
	 */
	public PluralAttributeBinding getPluralAttributeBinding();

	/**
	 * Retrieve the relational aspect of the element binding. Essentially describes the column(s) to which the
	 * binding maps the elements
	 *
	 * @return The relation information.
	 */
	public List<RelationalValueBinding> getRelationalValueBindings();

	/**
	 * Retrieves an enumeration describing the mapping nature of the collection's elements.
	 * 
	 * @return The nature enum.
	 */
	public Nature getNature();

	/**
	 * Retrieve the Hibernate type descriptor describing the mapping-typing of the elements.
	 *
	 * @return The element type descriptor.
	 */
	public HibernateTypeDescriptor getHibernateTypeDescriptor();

	/**
	 * Describes the nature of plural attribute elements in terms of relational implications.
	 *
	 * @author Steve Ebersole
	 * @author Gail Badner
	 */
	enum Nature {
		/**
		 * The collection elements are basic, simple values.
		 */
		BASIC( false ),
		/**
		 * The collection elements are compositions.
		 */
		AGGREGATION( false ),
		/**
		 * The collection elements represent entity's in a one-to-many association.
		 */
		ONE_TO_MANY,
		/**
		 * The collection elements represent entity's in a many-to-many association.
		 */
		MANY_TO_MANY,
		/**
		 * The collection elements represent entity's in a multi-valued ANY mapping.
		 */
		MANY_TO_ANY;

		private final boolean isAssociation;

		private Nature() {
			this( true );
		}

		private Nature(boolean association) {
			this.isAssociation = association;
		}

		public boolean isAssociation() {
			return isAssociation;
		}
	}
}
