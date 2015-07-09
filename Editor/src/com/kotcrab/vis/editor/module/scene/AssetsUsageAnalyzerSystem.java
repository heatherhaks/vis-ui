/*
 * Copyright 2014-2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotcrab.vis.editor.module.scene;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.utils.IntArray;
import com.kotcrab.vis.runtime.assets.VisAssetDescriptor;
import com.kotcrab.vis.runtime.component.AssetComponent;

/**
 * This system should be passive.
 * @author Kotcrab
 */
@Wire
public class AssetsUsageAnalyzerSystem extends EntityProcessingSystem {
	private ComponentMapper<AssetComponent> assetCm;

	private IntArray ids;
	private VisAssetDescriptor searchFor;

	public AssetsUsageAnalyzerSystem () {
		super(Aspect.all(AssetComponent.class));
	}

	@Override
	protected void process (Entity e) {
		if (assetCm.get(e).asset.compare(searchFor))
			ids.add(e.getId());
	}

	public void collectUsages (IntArray ids, VisAssetDescriptor searchFor) {
		this.ids = ids;
		this.searchFor = searchFor;
		process();
	}
}
