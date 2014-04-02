using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace Robo
{
    public class Camera : MapObject
    {
        private const float zoomUpperLimit = 2.5f;
        private const float zoomLowerLimit = .5f;

        private float _zoom;
        private Matrix _transform;
        private float _rotation;
        private int _viewportWidth;
        private int _viewportHeight;

        public Camera(string name,Viewport viewport, int screenWidth,
           int screenHeight, float initialZoom)
        {
            _zoom = initialZoom;
            _viewportWidth = viewport.Width;
            _viewportHeight = viewport.Height;
            this.Name = name;
            this.Rect = new Rectangle(0, 0, screenWidth, screenHeight);
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

        #endregion

        public Matrix GetTransformation()
        {
            displayRect = Rect;
            _transform =
               Matrix.CreateTranslation(new Vector3(-DisplayRect.X, -DisplayRect.Y, 0)) *
               Matrix.CreateRotationZ(Rotation) *
               Matrix.CreateScale(new Vector3(Zoom, Zoom, 1));

            return _transform;
        }
    }
}
