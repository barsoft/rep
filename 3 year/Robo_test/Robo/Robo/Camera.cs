using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace Robo
{
    public class Camera
    {
        private const float zoomUpperLimit = 2.5f;
        private const float zoomLowerLimit = .5f;

        private float _zoom;
        private Matrix _transform;
        private Vector2 _pos;
        private float _rotation;
        private int _viewportWidth;
        private int _viewportHeight;
        private int _worldWidth;
        private int _worldHeight;

        public Camera(Viewport viewport, int worldWidth,
           int worldHeight, float initialZoom)
        {
            _zoom = initialZoom;
            //_rotation = 0.0f;
            _pos = Vector2.Zero;
            _pos = new Vector2(0, 0);
            _viewportWidth = viewport.Width;
            _viewportHeight = viewport.Height;
            _worldWidth = worldWidth;
            _worldHeight = worldHeight;
        }

        #region Properties

        public float Zoom
        {
            get { return _zoom; }
            set
            {
                _zoom = value;
                if (_zoom < zoomLowerLimit)
                    _zoom = zoomLowerLimit;
                if (_zoom > zoomUpperLimit)
                    _zoom = zoomUpperLimit;
            }
        }

        public float Rotation
        {
            get { return _rotation; }
            set { _rotation = value; }
        }

        public void Move(Vector2 amount)
        {
            _pos += amount;
        }

        public Vector2 Pos
        {
            get { return _pos; }
            set
            {
                _pos = value;
            }
        }

        #endregion

        public Matrix GetTransformation()
        {
            _transform =
               Matrix.CreateTranslation(new Vector3(-_pos.X, -_pos.Y, 0)) *
               Matrix.CreateRotationZ(Rotation) *
               Matrix.CreateScale(new Vector3(Zoom, Zoom, 1));

            return _transform;
        }
    }
}
